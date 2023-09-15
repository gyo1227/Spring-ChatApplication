package com.chatapplication.server.global.config.jwt;

import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.ErrorCode;
import com.chatapplication.server.global.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            if(e.getMessage().equals(ErrorCode.MALFORMED_JWT.getMessage())) {
                setErrorResponse(response, ErrorCode.MALFORMED_JWT, ResponseMessage.MALFORMED_JWT);
            }
            if(e.getMessage().equals(ErrorCode.EXPIRED_JWT.getMessage())) {
                setErrorResponse(response, ErrorCode.EXPIRED_JWT, ResponseMessage.EXPIRED_JWT);
            }
            if(e.getMessage().equals(ErrorCode.UNSUPPORTED_JWT.getMessage())) {
                setErrorResponse(response, ErrorCode.UNSUPPORTED_JWT, ResponseMessage.UNSUPPORTED_JWT);
            }
            if(e.getMessage().equals(ErrorCode.BAD_TOKEN.getMessage())) {
                setErrorResponse(response, ErrorCode.BAD_TOKEN, ResponseMessage.BAD_TOKEN);
            }
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode, ResponseMessage message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .statusText(errorCode.getStatus().name())
                .code(errorCode)
                .message(message.getMessage())
                .build();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
