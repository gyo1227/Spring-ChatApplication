package com.chatapplication.server.domain.member.exceotion;

import com.chatapplication.server.global.constant.ResponseMessage;

public class MemberNotFoundException extends IllegalArgumentException {

    public MemberNotFoundException() {
        super(ResponseMessage.MEMBER_NOT_FOUND.getMessage());
    }

}
