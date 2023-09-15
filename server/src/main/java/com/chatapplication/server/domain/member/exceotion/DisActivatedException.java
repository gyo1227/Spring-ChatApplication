package com.chatapplication.server.domain.member.exceotion;

import com.chatapplication.server.global.constant.ResponseMessage;

public class DisActivatedException extends RuntimeException {
    public DisActivatedException() {
        super(ResponseMessage.DISACTIVATED_MEMBER.getMessage());
    }
}
