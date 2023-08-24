import React, { useEffect } from "react";
import SignupForm from "../components/SignupForm";

const SignupContainer = () => {
  const onChange = (e) => {
    const { name, value } = e.target;
    console.log(`name: ${name}, value: ${value}`);
  };

  const onBlur = (e) => {
    const { name, value } = e.target;
    console.log(`name: ${name}, value: ${value}`);
  };

  const onSubmit = (e) => {
    e.preventDefault();
  };

  // 컴포넌트 처음 로딩 시 실행
  useEffect(() => {}, []);

  // 회원가입 성공 / 실패 처리
  useEffect(() => {}, []);

  return <SignupForm onChange={onChange} onBlur={onBlur} onSubmit={onSubmit} />;
};

export default SignupContainer;
