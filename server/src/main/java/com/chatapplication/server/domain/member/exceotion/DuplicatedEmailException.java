package com.chatapplication.server.domain.member.exceotion;

import com.chatapplication.server.global.constant.ResponseMessage;

public class DuplicatedEmailException extends IllegalArgumentException {

    public DuplicatedEmailException() {
        super(ResponseMessage.DUPLICATED_EMAIL.getMessage());
    }
}
