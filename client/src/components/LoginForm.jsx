import React from "react";
import { Link } from "react-router-dom";
import { styled } from "styled-components";
import { PAGE_PATH } from "../utils/constants";

const LoginFormWrapper = styled.div`
  h3 {
    text-align: center;
  }

  form {
    display: flex;
    flex-direction: column;
  }
`;

const LoginText = styled.h3`
  font-size: 22px;
  font-weight: 600;
  margin: 0 0 30px;
`;

const LoginInput = styled.input`
  width: 400px;
  height: 50px;
  border: 2px solid #e0e0e0;
  border-radius: 24px;
  font-size: 16px;
  padding: 0 16px;
  margin: 7px 0;
  &:hover {
    border: 2px solid #000;
  }
`;

const LoginButton = styled.button`
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

const SignupPageLink = styled(Link)`
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

const ErrorMessage = styled.div`
  color: red;
  font-size: 13px;
  padding: 0 14px;
`;

const LoginForm = ({ form, onChange, onSubmit, error }) => {
  return (
    <LoginFormWrapper>
      <LoginText>LOGIN</LoginText>
      <form onSubmit={onSubmit}>
        <LoginInput
          type="text"
          placeholder="이메일을 입력해주세요."
          name="email"
          value={form.email}
          onChange={onChange}
        />
        <LoginInput
          type="password"
          placeholder="비밀번호를 입력해주세요."
          name="password"
          value={form.password}
          onChange={onChange}
        />
        {error !== null && <ErrorMessage>{error.message}</ErrorMessage>}
        <LoginButton>로그인</LoginButton>
      </form>
      <SignupPageLink to={PAGE_PATH.SIGNUP}>
        Don't have an account?
        <span> Create a free account.</span>
      </SignupPageLink>
    </LoginFormWrapper>
  );
};

export default LoginForm;
