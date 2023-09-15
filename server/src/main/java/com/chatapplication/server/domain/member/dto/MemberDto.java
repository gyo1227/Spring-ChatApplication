package com.chatapplication.server.domain.member.dto;

import com.chatapplication.server.domain.member.entity.Authority;
import com.chatapplication.server.domain.member.entity.Member;
import com.chatapplication.server.global.constant.MemberStatus;
import com.chatapplication.server.global.constant.ProviderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String tag;
    private String phoneNumber;
    private String imageUrl;
    private ProviderType providerType;
    private MemberStatus activated;
    private Set<Authority> authorities;

    @Builder
    public MemberDto(Long id, String email, String password, String nickname, String tag, String phoneNumber, String imageUrl, ProviderType providerType, MemberStatus activated, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tag = tag;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.providerType = providerType;
        this.activated = activated;
        this.authorities = authorities;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .tag(tag)
                .phoneNumber(phoneNumber)
                .imageUrl(imageUrl)
                .providerType(providerType)
                .activated(activated)
                .authorities(authorities)
                .build();
    }
}
