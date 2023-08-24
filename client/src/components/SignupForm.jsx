import React from "react";
import { Link } from "react-router-dom";
import { styled } from "styled-components";

const SignupFormWrapper = styled.div`
  h3 {
    text-align: center;
  }

  form {
    display: flex;
    flex-direction: column;
  }
`;

const SignupText = styled.h3`
  font-size: 22px;
  font-weight: 600;
  margin: 0 0 30px;
`;

const SignupInput = styled.input`
  width: 460px;
  height: 50px;
  border: 2px solid #e0e0e0;
  border-radius: 24px;
  font-size: 16px;
  padding: 0 14px;
  margin: 7px 0;
  &:hover {
    border: 2px solid #000;
  }
`;

const SignupButton = styled.button`
  cursor: pointer;
  width: 100%;
  height: 50px;
  background: #216ce7;
  color: #fff;
  padding: 0 16px;
  margin-top: 14px;
  border: none;
  border-radius: 24px;
  text-align: center;
  font-size: 1rem;
  font-weight: 600;
  letter-spacing: 2px;
  transition: all 0.375s;

  &:hover {
    background-color: #143d81;
  }
`;

const LoginPageLink = styled(Link)`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  cursor: pointer;
  color: #7c7c7c;
  text-decoration: none;
  transition: all 0.375s;

  &:hover {
    text-decoration: underline;
  }

  span {
    color: #216ce7;
  }
`;

const SignupForm = ({ onChange, onBlur, onSubmit }) => {
  return (
    <SignupFormWrapper>
      <SignupText>SIGN UP</SignupText>
      <form onSubmit={onSubmit}>
        <SignupInput
          type="text"
          placeholder="이메일을 입력해주세요."
          name="email"
          onChange={onChange}
          onBlur={onBlur}
        />
        <SignupInput
          type="password"
          placeholder="비밀번호를 입력해주세요."
          name="password"
          onChange={onChange}
          onBlur={onBlur}
        />
        <SignupInput
          type="text"
          placeholder="닉네임을 입력해주세요."
          name="nickname"
          onChange={onChange}
          onBlur={onBlur}
        />
        <SignupInput
          type="text"
          placeholder="전화번호를 입력해주세요."
          name="phoneNumber"
          onChange={onChange}
          onBlur={onBlur}
        />
        <SignupButton>회원가입</SignupButton>
      </form>
      <LoginPageLink to={`/login`}>
        Already have an account?
        <span>Sign in</span>
      </LoginPageLink>
    </SignupFormWrapper>
  );
};

export default SignupForm;
