package com.chatapplication.server.domain.member.service;

import com.chatapplication.server.domain.member.dto.MemberDto;
import com.chatapplication.server.domain.member.entity.Authority;
import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.domain.member.exceotion.*;
import com.chatapplication.server.domain.member.repository.AuthRepository;
import com.chatapplication.server.domain.member.repository.RedisRepository;
import com.chatapplication.server.global.config.jwt.JwtProvider;
import com.chatapplication.server.global.constant.MemberStatus;
import com.chatapplication.server.global.constant.ProviderType;
import com.chatapplication.server.global.constant.MemberRole;
import com.chatapplication.server.global.dto.jwt.TokenDto;
import com.chatapplication.server.global.exception.jwt.NoSavedRefreshTokenException;
import com.chatapplication.server.global.exception.jwt.NoMatchedRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisRepository redisRepository;

    public final int REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 14;

    public void signup(String email, String password, String nickname, String phoneNumber) {
        duplicatedCheckEmail(email, ProviderType.LOCAL);
        duplicatedCheckPhoneNumber(phoneNumber, ProviderType.LOCAL);

        String tag = createRandomTag(nickname);

        Authority authority = Authority.builder()
                .authority(MemberRole.ROLE_USER.name())
                .build();

        MemberDto memberDto = MemberDto.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .tag(tag)
                .phoneNumber(phoneNumber)
                .providerType(ProviderType.LOCAL)
                .activated(MemberStatus.ACTIVATED)
                .authorities(Collections.singleton(authority))
                .build();
        authRepository.save(memberDto.toEntity());
    }

    public void duplicatedCheckEmail(String email, ProviderType providerType) {
        authRepository.findByEmailAndProviderType(email, providerType)
                .ifPresent(member -> {
                    throw new DuplicatedEmailException();
                });
    }

    public void duplicatedCheckPhoneNumber(String phoneNumber, ProviderType providerType) {
        authRepository.findByPhoneNumberAndProviderType(phoneNumber, providerType)
                .ifPresent(member -> {
                    throw new DuplicatedPhoneNumberException();
                });
    }

    public String createRandomTag(String nickname) {
        Random random = new Random();
        boolean isTagFound = false;
        int randomNumber;
        StringBuilder tag = new StringBuilder();

        while(!isTagFound) {
            for(int i = 0; i < 4; i++) {
                randomNumber = random.nextInt(9);
                tag.append(randomNumber);
            }
            if(authRepository.findByNicknameAndTag(nickname, tag.toString()).isEmpty()) {
                isTagFound = true;
            }
        }
        return tag.toString();
    }

    public TokenDto login(String email, String password) {
        Member member = authRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordNotMatchedException();
        }

        if(member.getActivated().equals(MemberStatus.DISACTIVATED)) {
            throw new DisActivatedException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.createToken(authentication);
    }

    public ResponseCookie createCookie(String type, String token) {
        return ResponseCookie.from(type, token)
                .path("/")
                .secure(true)
                .httpOnly(true)
                .maxAge(REFRESH_TOKEN_EXPIRATION_TIME)
                .build();
    }

    public TokenDto reissue(String refreshToken) {
        jwtProvider.validateToken(refreshToken);
        Authentication authentication = jwtProvider.getAuthentication(refreshToken);
        String savedRefreshToken = redisRepository.findByKey(authentication.getName()).orElseThrow(NoSavedRefreshTokenException::new);

        if(!savedRefreshToken.equals(refreshToken)) {
            redisRepository.deleteByKey(authentication.getName());
            throw new NoMatchedRefreshTokenException();
        }
        return jwtProvider.createToken(authentication);
    }
}
