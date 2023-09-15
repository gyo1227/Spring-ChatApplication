package com.chatapplication.server.global.dto.response;

import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.SuccessCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class SuccessResponse {

    private final SuccessCode code;
    private final int status;
    private final String statusText;
    private final String message;

    @Builder
    public SuccessResponse(SuccessCode code, int status, String statusText, String message) {
        this.code = code;
        this.status = status;
        this.statusText = statusText;
        this.message = message;
    }

    public static ResponseEntity<SuccessResponse> toResponseEntity(SuccessCode successCode, ResponseMessage message) {
        SuccessResponse response = SuccessResponse.builder()
                .status(successCode.getStatus().value())
                .statusText(successCode.getStatus().name())
                .code(successCode)
                .message(message.getMessage())
                .build();

        return ResponseEntity.status(successCode.getStatus()).body(response);
    }


}
