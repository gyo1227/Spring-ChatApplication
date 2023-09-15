package com.chatapplication.server.global.constant.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("ME001", HttpStatus.BAD_REQUEST, "BAD REQUEST"),
    VALIDATION_ERROR("ME002", CONFLICT, "VALIDATION ERROR"),
    DUPLICATED_EMAIL("ME003", CONFLICT, "DUPLICATED EMAIL EXISTS"),
    DUPLICATED_PHONENUMBER("ME004", CONFLICT, "DUPLICATED PHONENUMBER EXISTS"),
    MALFORMED_JWT("ME005", HttpStatus.UNAUTHORIZED, "MALFORMED JWT"),
    EXPIRED_JWT("ME006", HttpStatus.UNAUTHORIZED, "EXPIRED JWT"),
    UNSUPPORTED_JWT("ME007", HttpStatus.UNAUTHORIZED, "UNSUPPORTED JWT"),
    BAD_TOKEN("ME008", HttpStatus.UNAUTHORIZED, "BAD TOKEN"),
    MEMBER_NOT_FOUND("ME009", HttpStatus.BAD_REQUEST, "MEMBER NOT FOUND"),
    PASSWORD_NOT_MATCHED("ME0010", HttpStatus.BAD_REQUEST, "PASSWORD NOT MATCH"),
    MEMBER_DISACTIVATED("ME0011", HttpStatus.FORBIDDEN, "MEMBER DISACTIVATED"),
    NO_EXIST_REFRESHTOKEN("ME012", HttpStatus.BAD_REQUEST, "NO EXIST REFRESHTOKEN"),
    NO_SAVED_REFRESHTOKEN("ME0013", HttpStatus.BAD_REQUEST, "NO SAVED REFRESHTOKEN"),
    NO_MATCH_REFRESHTOKEN("ME014", HttpStatus.BAD_REQUEST, "NOT MATCH REFRESHTOKEN"),
    ;

    private final String code;
    private final HttpStatus status;
    private final String message;
}
