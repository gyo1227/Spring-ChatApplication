import React, { useEffect } from "react";
import LoginForm from "../components/LoginForm";

const LoginContainer = () => {
  const onChange = (e) => {
    const { name, value } = e.target;
    console.log(`name: ${name}, value: ${value}`);
  };

  const onSubmit = (e) => {
    e.preventDefault();
  };

  // 컴포넌트 처음 로딩 시 실행
  useEffect(() => {}, []);

  // 로그인 성공 / 실패 처리
  useEffect(() => {}, []);
  return <LoginForm onChange={onChange} onSubmit={onSubmit} />;
};

export default LoginContainer;
