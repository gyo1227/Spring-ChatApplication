package com.chatapplication.server.global.dto.response;

import com.chatapplication.server.global.constant.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
public class ErrorDataResponse {

    private final ErrorCode code;
    private final int status;
    private final String statusText;
    private final String message;
    private final Map<String, String> errors;

    @Builder
    public ErrorDataResponse(ErrorCode code, int status, String statusText, String message, Map<String, String> errors) {
        this.code = code;
        this.status = status;
        this.statusText = statusText;
        this.message = message;
        this.errors = errors;
    }

    public static ResponseEntity<ErrorDataResponse> toResponseEntity(ErrorCode errorCode, String message, Map<String, String> errors) {
        ErrorDataResponse response = ErrorDataResponse.builder()
                .status(errorCode.getStatus().value())
                .statusText(errorCode.getStatus().name())
                .code(errorCode)
                .message(message)
                .errors(errors)
                .build();

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}
