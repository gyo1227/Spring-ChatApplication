package com.chatapplication.server.global.exception.jwt;

import com.chatapplication.server.global.constant.ResponseMessage;

public class NoSavedRefreshTokenException extends IllegalArgumentException {

    public NoSavedRefreshTokenException() {
        super(ResponseMessage.NO_SAVED_REFRESHTOKEN.getMessage());
    }
}
