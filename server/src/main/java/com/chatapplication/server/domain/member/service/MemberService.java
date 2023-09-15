package com.chatapplication.server.domain.member.service;

import com.chatapplication.server.domain.member.dto.MemberInfoResponseDto;
import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.domain.member.exceotion.MemberNotFoundException;
import com.chatapplication.server.domain.member.repository.MemberRepository;
import com.chatapplication.server.global.constant.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberInfoResponseDto getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }
        Member member = memberRepository.findByEmailAndActivated(authentication.getName(), MemberStatus.ACTIVATED)
                .orElseThrow(MemberNotFoundException::new);

        return MemberInfoResponseDto.builder()
                .nickname(member.getNickname())
                .imageUrl(member.getImageUrl())
                .build();
    }
}
