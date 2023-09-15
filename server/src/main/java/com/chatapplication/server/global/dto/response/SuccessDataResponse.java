package com.chatapplication.server.global.dto.response;

import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.SuccessCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


@Getter
public class SuccessDataResponse {

    private final SuccessCode code;
    private final int status;
    private final String statusText;
    private final String message;
    private final Object data;

    @Builder
    public SuccessDataResponse(SuccessCode code, int status, String statusText, String message, Object data) {
        this.code = code;
        this.status = status;
        this.statusText = statusText;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<SuccessDataResponse> toResponseEntity(SuccessCode successCode, ResponseMessage message, Object data) {
        SuccessDataResponse response = SuccessDataResponse.builder()
                .status(successCode.getStatus().value())
                .statusText(successCode.getStatus().name())
                .code(successCode)
                .message(message.getMessage())
                .data(data)
                .build();

        return ResponseEntity.status(successCode.getStatus()).body(response);
    }

    public static ResponseEntity<SuccessDataResponse> toResponseEntity(SuccessCode successCode, ResponseMessage message, HttpHeaders headers, Object data) {
        SuccessDataResponse response = SuccessDataResponse.builder()
                .status(successCode.getStatus().value())
                .statusText(successCode.getStatus().name())
                .code(successCode)
                .message(message.getMessage())
                .data(data)
                .build();

        return ResponseEntity.status(successCode.getStatus()).headers(headers).body(response);
    }
}
