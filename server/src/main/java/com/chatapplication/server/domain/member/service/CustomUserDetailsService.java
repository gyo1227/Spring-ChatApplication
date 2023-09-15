package com.chatapplication.server.domain.member.service;

import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.domain.member.exceotion.DisActivatedException;
import com.chatapplication.server.domain.member.repository.AuthRepository;
import com.chatapplication.server.global.constant.MemberStatus;
import com.chatapplication.server.global.constant.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.MEMBER_NOT_FOUND.getMessage()));
    }

    private User createUser(Member member) {
        if(member.getActivated().equals(MemberStatus.DISACTIVATED)) {
            throw new DisActivatedException();
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
