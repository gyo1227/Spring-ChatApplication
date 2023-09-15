package com.chatapplication.server.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRole {
    ROLE_USER("사용자"),
    ROLE_ADMIN("관리자");

    private final String role;
}
