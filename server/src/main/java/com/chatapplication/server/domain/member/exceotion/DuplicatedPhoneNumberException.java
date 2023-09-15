package com.chatapplication.server.domain.member.exceotion;

import com.chatapplication.server.global.constant.ResponseMessage;

public class DuplicatedPhoneNumberException extends IllegalArgumentException {

    public DuplicatedPhoneNumberException() {
        super(ResponseMessage.DUPLICATED_PHONENUMBER.getMessage());
    }
}