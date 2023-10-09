import React, { useEffect } from "react";
import LoginForm from "../components/LoginForm";
import { useDispatch, useSelector } from "react-redux";
import * as authAction from "../store/actions/auth";
import * as userAction from "../store/actions/user";
import { useNavigate } from "react-router-dom";
import { PAGE_PATH } from "../utils/constants";

const LoginContainer = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const form = useSelector((state) => state.auth.form.login);
  const error = useSelector((state) => state.auth.error.login);
  const response = useSelector((state) => state.auth.response);

  const onChange = (e) => {
    const { name, value } = e.target;
    dispatch(
      authAction.changeField({
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
        authAction.setLoginError({
          message: "아이디를 입력해 주세요.",
        })
      );
      return;
    }

    if (password === "") {
      dispatch(
        authAction.setLoginError({
          message: "비밀번호를 입력해 주세요.",
        })
      );
      return;
    }
    dispatch(authAction.login(form));
  };

  // 컴포넌트 처음 로딩 시 실행
  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      // localStorage에 토큰 있는 경우
      dispatch(userAction.setAccessToken(accessToken));
    } else {
      dispatch(authAction.initializeForm());
    }
  }, [dispatch]);

  // 로그인 성공 / 실패 처리
  useEffect(() => {
    // 로그인 성공
    if (response.code === "LOGIN_SUCCESSFUL") {
      localStorage.setItem("accessToken", response.data);
      dispatch(userAction.setAccessToken(response.data));
      navigate(PAGE_PATH.MYCHATROOMS);

      return () => {
        dispatch(authAction.initializeForm());
      };
    }

    // 로그인 실패
    if (
      response.code === "MEMBER_NOT_FOUND" ||
      response.code === "PASSWORD_NOT_MATCHED"
    ) {
      dispatch(
        authAction.setLoginError({
          message: "아이디 또는 비밀번호를 잘못 입력했습니다.",
        })
      );
    }
  }, [response, dispatch, navigate]);
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
