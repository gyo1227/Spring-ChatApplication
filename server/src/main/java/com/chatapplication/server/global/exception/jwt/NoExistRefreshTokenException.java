package com.chatapplication.server.global.exception.jwt;

import com.chatapplication.server.global.constant.ResponseMessage;

public class NoExistRefreshTokenException extends IllegalArgumentException {

    public NoExistRefreshTokenException() {
        super(ResponseMessage.NO_EXIST_REFRESHTOKEN.getMessage());
    }
}
