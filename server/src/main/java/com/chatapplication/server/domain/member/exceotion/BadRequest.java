package com.chatapplication.server.domain.member.exceotion;

public class BadRequest extends IllegalArgumentException {

    public BadRequest(String message) {
        super(message);
    }
}
