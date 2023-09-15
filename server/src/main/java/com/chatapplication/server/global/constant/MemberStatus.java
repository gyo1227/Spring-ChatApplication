package com.chatapplication.server.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberStatus {
    ACTIVATED("활성화"),
    DISACTIVATED("비활성화");

    private final String status;
}
