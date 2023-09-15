package com.chatapplication.server.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {

    private final String nickname;
    private final String imageUrl;

    @Builder
    public MemberInfoResponseDto(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
