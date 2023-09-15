package com.chatapplication.server.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {

    SIGNUP_SUCCESS("성공적으로 가입되었습니다."),
    LOGIN_SUCCESS("로그인에 성공하였습니다."),
    ACCESSTOKEN_CHECK_SUCCESS("토큰 검증에 성공하였습니다."),
    REISSUE_SUCCESS("토큰 재발급에 성공하였습니다."),

    AVAILABLE_EMAIL("사용 가능한 이메일 입니다."),
    AVAILABLE_PHONENUMBER("사용 가능한 전화번호 입니다."),


    BAD_REQUEST("중복체크 키값이 잘못되었습니다"),
    VALIDATION_ERROR("입력값이 올바르지 않습니다."),
    DUPLICATED_EMAIL("이미 등록된 이메일 입니다."),
    DUPLICATED_PHONENUMBER("이미 등록된 전화번호 입니다."),
    MEMBER_NOT_FOUND("해당하는 사용자를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다."),
    MALFORMED_JWT("잘못된 형식의 토큰입니다."),
    EXPIRED_JWT("토큰이 만료되었습니다."),
    UNSUPPORTED_JWT("지원하지 않는 토큰입니다."),
    BAD_TOKEN("잘못된 토큰입니다."),
    NO_EXIST_REFRESHTOKEN("RefreshToken이 존재하지 않습니다."),
    NO_SAVED_REFRESHTOKEN("저장된 RefreshToken이 없습니다."),
    NOT_MATCH_REFRESHTOKEN("RefreshToken이 일치하지 않습니다."),
    DISACTIVATED_MEMBER("비활성화 된 사용자 입니다."),

    ;

    private final String message;
}
