package com.chatapplication.server.domain.member.exceotion;

import com.chatapplication.server.global.constant.ResponseMessage;

public class PasswordNotMatchedException extends IllegalArgumentException {

    public PasswordNotMatchedException() {
        super(ResponseMessage.PASSWORD_NOT_MATCH.getMessage());
    }
}
