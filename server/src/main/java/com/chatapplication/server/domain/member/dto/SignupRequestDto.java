package com.chatapplication.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @Pattern(
            regexp="^[a-z0-9]+@[a-z]+.[a-z]{2,3}$",
            message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!])[A-Za-z\\d@!]{8,16}$",
            message = "8~16자의 영문 대/소문자, 숫자, 특수문자(!),(@)만 사용 가능합니다")
    private String password;

    @Pattern(
            regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
            message = "2~16자의 한글, 영문 대/소문자, 숫자만 사용 가능합니다")
    private String nickname;

    @Pattern(
            regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
            message = "휴대전화번호가 정확한지 확인해 주세요")
    private String phoneNumber;

    public MemberDto toDto() {
        return MemberDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .build();
    }
}
