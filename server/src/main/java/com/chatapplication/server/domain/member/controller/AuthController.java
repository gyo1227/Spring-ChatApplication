package com.chatapplication.server.domain.member.controller;

import com.chatapplication.server.domain.member.dto.LoginRequestDto;
import com.chatapplication.server.domain.member.dto.SignupRequestDto;
import com.chatapplication.server.domain.member.exceotion.BadRequest;
import com.chatapplication.server.domain.member.service.AuthService;
import com.chatapplication.server.global.constant.ProviderType;
import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.SuccessCode;
import com.chatapplication.server.global.dto.jwt.TokenDto;
import com.chatapplication.server.global.dto.response.SuccessDataResponse;
import com.chatapplication.server.global.dto.response.SuccessResponse;
import com.chatapplication.server.global.exception.jwt.NoExistRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping("/check/duplicated")
    public ResponseEntity<SuccessResponse> duplicatedCheck(@RequestParam String key, @RequestParam String value) {
        switch(key) {
            case "email":
                authService.duplicatedCheckEmail(value, ProviderType.LOCAL);
                return SuccessResponse.toResponseEntity(SuccessCode.DUPLICATED_EMAIL_CHECK_SUCCESSFUL, ResponseMessage.AVAILABLE_EMAIL);
            case "phoneNumber":
                authService.duplicatedCheckPhoneNumber(value, ProviderType.LOCAL);
                return SuccessResponse.toResponseEntity(SuccessCode.DUPLICATED_PHONENUMBER_CHECK_SUCCESSFUL, ResponseMessage.AVAILABLE_PHONENUMBER);
            default:
                throw new BadRequest("중복 체크 키값 에러");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        authService.signup(requestDto.getEmail(), requestDto.getPassword(), requestDto.getNickname(), requestDto.getPhoneNumber());
        return SuccessResponse.toResponseEntity(SuccessCode.SIGNUP_SUCCESSFUL, ResponseMessage.SIGNUP_SUCCESS);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessDataResponse> login(@RequestBody LoginRequestDto requestDto) {
        TokenDto tokenDto = authService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Set-Cookie", authService.createCookie("refreshToken", tokenDto.getRefreshToken()).toString());
        httpHeaders.add("Authorization", "Bearer " + tokenDto.getAccessToken());

        return SuccessDataResponse.toResponseEntity(SuccessCode.LOGIN_SUCCESSFUL, ResponseMessage.LOGIN_SUCCESS, httpHeaders, tokenDto.getAccessToken());
    }

    @PostMapping("/reissue")
    public ResponseEntity<SuccessDataResponse> reissue(@CookieValue(value = "refreshToken", required = false) Cookie refreshToken) {
        if(refreshToken == null) {
            throw new NoExistRefreshTokenException();
        }

        TokenDto tokenDto = authService.reissue(refreshToken.getValue());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Set-Cookie", authService.createCookie("refreshToken", tokenDto.getRefreshToken()).toString());
        httpHeaders.add("Authorization", "Bearer " + tokenDto.getAccessToken());

        return SuccessDataResponse.toResponseEntity(SuccessCode.REISSUE_SUCCESSFUL, ResponseMessage.REISSUE_SUCCESS, httpHeaders, tokenDto.getAccessToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok("");
    }
}
