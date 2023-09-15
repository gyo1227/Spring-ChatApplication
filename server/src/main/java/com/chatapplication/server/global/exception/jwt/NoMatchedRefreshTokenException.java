package com.chatapplication.server.global.exception.jwt;

import com.chatapplication.server.global.constant.ResponseMessage;

public class NoMatchedRefreshTokenException extends IllegalArgumentException {

    public NoMatchedRefreshTokenException() {
        super(ResponseMessage.NOT_MATCH_REFRESHTOKEN.getMessage());
    }
}
