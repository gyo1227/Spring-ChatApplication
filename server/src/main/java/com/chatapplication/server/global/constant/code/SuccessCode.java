package com.chatapplication.server.global.constant.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum SuccessCode {

    SIGNUP_SUCCESSFUL("MS001",HttpStatus.CREATED,"SIGNUP SUCCESS"),
    LOGIN_SUCCESSFUL("MS002", HttpStatus.CREATED, "LOGIN SUCCESS"),
    DUPLICATED_EMAIL_CHECK_SUCCESSFUL("MS003", HttpStatus.OK, "DUPLICATED EMAIL CHECK SUCCESS"),
    DUPLICATED_PHONENUMBER_CHECK_SUCCESSFUL("MS004", HttpStatus.OK, "DUPLICATED PHONENUMBER CHECK SUCCESS"),
    ACCESSTOKEN_CHECK_SUCCESSFUL("MS005", HttpStatus.OK, "ACCESSTOKEN CHECK SUCCESSFUL"),
    REISSUE_SUCCESSFUL("MS006",HttpStatus.CREATED, "REISSUE SUCCESSFUL")
    ;

    private final String code;
    private final HttpStatus status;
    private final String message;
}
