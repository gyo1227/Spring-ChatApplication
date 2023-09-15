package com.chatapplication.server.global.dto.response;

import com.chatapplication.server.global.constant.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {

    private final ErrorCode code;
    private final int status;
    private final String statusText;
    private final String message;

    @Builder
    public ErrorResponse(ErrorCode code, int status, String statusText, String message) {
        this.code = code;
        this.status = status;
        this.statusText = statusText;
        this.message = message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode, String message) {
        ErrorResponse response = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .statusText(errorCode.getStatus().name())
                .code(errorCode)
                .message(message)
                .build();

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}
