package com.chatapplication.server.global.exception;

import com.chatapplication.server.domain.member.exceotion.*;
import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.ErrorCode;
import com.chatapplication.server.global.dto.response.ErrorDataResponse;
import com.chatapplication.server.global.dto.response.ErrorResponse;
import com.chatapplication.server.global.exception.jwt.NoSavedRefreshTokenException;
import com.chatapplication.server.global.exception.jwt.NoExistRefreshTokenException;
import com.chatapplication.server.global.exception.jwt.NoMatchedRefreshTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    
    // @Valid 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDataResponse> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return ErrorDataResponse.toResponseEntity(ErrorCode.VALIDATION_ERROR, ResponseMessage.VALIDATION_ERROR.getMessage(), errors);
    }

    // 잘못된 요청
    @ExceptionHandler(BadRequest.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestException(BadRequest e) {
        return ErrorResponse.toResponseEntity(ErrorCode.BAD_REQUEST, e.getMessage());
    }

    // 이메일 중복 에러
    @ExceptionHandler(DuplicatedEmailException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicatedEmailException(DuplicatedEmailException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.DUPLICATED_EMAIL, e.getMessage());
    }

    // 전화번호 중복 에러
    @ExceptionHandler(DuplicatedPhoneNumberException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicatedPhoneNumberException(DuplicatedPhoneNumberException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.DUPLICATED_PHONENUMBER, e.getMessage());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.MEMBER_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public final ResponseEntity<ErrorResponse> handlePasswordNotMatchedException(PasswordNotMatchedException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.PASSWORD_NOT_MATCHED, e.getMessage());
    }

    @ExceptionHandler(DisActivatedException.class)
    public final ResponseEntity<ErrorResponse> handleDisActivatedException(DisActivatedException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.MEMBER_DISACTIVATED, e.getMessage());
    }

    @ExceptionHandler(NoExistRefreshTokenException.class)
    public final ResponseEntity<ErrorResponse> handleNoExistRefreshTokenException(NoExistRefreshTokenException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.NO_EXIST_REFRESHTOKEN, e.getMessage());
    }

    @ExceptionHandler(NoSavedRefreshTokenException.class)
    public final ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(NoSavedRefreshTokenException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.NO_SAVED_REFRESHTOKEN, e.getMessage());
    }

    @ExceptionHandler(NoMatchedRefreshTokenException.class)
    public final ResponseEntity<ErrorResponse> handleTokenNotMatchedException(NoMatchedRefreshTokenException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.NO_MATCH_REFRESHTOKEN, e.getMessage());
    }

}
