import React, { useEffect } from "react";
import LoginForm from "../components/LoginForm";
import { useDispatch, useSelector } from "react-redux";
import {
  changeField,
  initializeForm,
  login,
  setLoginError,
} from "../store/actions/auth";

const LoginContainer = () => {
  const dispatch = useDispatch();

  const form = useSelector((state) => state.auth.form.login);
  const error = useSelector((state) => state.auth.error.login);

  const onChange = (e) => {
    const { name, value } = e.target;
    dispatch(
      changeField({
        form: "login",
        key: name,
        value: value,
      })
    );
  };

  const onSubmit = (e) => {
    e.preventDefault();
    const { email, password } = form;

    if (email === "") {
      dispatch(
        setLoginError({
          message: "아이디를 입력해 주세요.",
        })
      );
      return;
    }

    if (password === "") {
      dispatch(
        setLoginError({
          message: "비밀번호를 입력해 주세요.",
        })
      );
      return;
    }
    dispatch(login(form));
  };

  // 컴포넌트 처음 로딩 시 실행
  useEffect(() => {
    dispatch(initializeForm("login"));
  }, [dispatch]);

  // 로그인 성공 / 실패 처리
  useEffect(() => {}, []);
  return (
    <LoginForm
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
      error={error}
    />
  );
};

export default LoginContainer;
