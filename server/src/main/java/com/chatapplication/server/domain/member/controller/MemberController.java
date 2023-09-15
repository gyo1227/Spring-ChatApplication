package com.chatapplication.server.domain.member.controller;

import com.chatapplication.server.domain.member.dto.MemberInfoResponseDto;
import com.chatapplication.server.domain.member.service.MemberService;
import com.chatapplication.server.global.constant.ResponseMessage;
import com.chatapplication.server.global.constant.code.SuccessCode;
import com.chatapplication.server.global.dto.response.SuccessDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<SuccessDataResponse> getMyInfo() {
        MemberInfoResponseDto memberInfoResponseDto = memberService.getMyInfo();
        return SuccessDataResponse.toResponseEntity(SuccessCode.ACCESSTOKEN_CHECK_SUCCESSFUL, ResponseMessage.ACCESSTOKEN_CHECK_SUCCESS, memberInfoResponseDto);
    }
}
