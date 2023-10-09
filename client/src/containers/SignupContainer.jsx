import React, { useEffect } from "react";
import SignupForm from "../components/SignupForm";
import { useDispatch, useSelector } from "react-redux";
import * as authAction from "../store/actions/auth";
import * as userAction from "../store/actions/user";
import { useNavigate } from "react-router-dom";

const formKeyMap = {
  email: "이메일",
  password: "비밀번호",
  nickname: "닉네임",
  phoneNumber: "휴대전화번호",
};

const regexMap = {
  email: /^[a-z0-9]+@[a-z]+.[a-z]{2,3}$/,
  password: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!])[A-Za-z\d@!]{8,16}$/,
  nickname: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/,
  phoneNumber1: /^010-?([0-9]{4})-?([0-9]{4})$/,
  phoneNumber2: /^01([1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/,
};

const regexErrorMap = {
  email: "이메일 형식에 맞지 않습니다.",
  password: "8~16자의 영문 대/소문자, 숫자, 특수문자(!),(@)만 사용 가능합니다",
  nickname: "2~16자의 한글, 영문 대/소문자, 숫자만 사용 가능합니다",
  phoneNumber: "휴대전화번호가 정확한지 확인해 주세요",
};

const SignupContainer = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const form = useSelector((state) => state.auth.form.signup);
  const error = useSelector((state) => state.auth.error.signup);
  const response = useSelector((state) => state.auth.response);

  const onChange = (e) => {
    const { name, value } = e.target;
    dispatch(
      authAction.changeField({
        form: "signup",
        key: name,
        value: value,
      })
    );
  };

  const onBlur = (e) => {
    const { name, value } = e.target;
    regexCheck(name, value);
  };

  // 정규식 검사 함수
  const regexCheck = (name, value) => {
    if (value === "") {
      dispatch(
        authAction.setSignupError({
          key: name,
          message: `${formKeyMap[name]}: 필수 정보입니다.`,
        })
      );
    } else {
      if (name === "phoneNumber") {
        // 휴대전화번호 정규식 검사
        const firstNum = value.substr(0, 3);

        if (firstNum === "010") {
          // 010으로 시작 할 경우
          if (regexMap.phoneNumber1.test(value)) {
            // 정규식 통과 성공
            if (!value.includes("-")) {
              // - 없는 경우
              dispatch(
                authAction.changeField({
                  form: "signup",
                  key: name,
                  value: value.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3"),
                })
              );
            }
            // 휴대전화번호 중복 체크
            duplicatedCheck(
              name,
              value.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3")
            );
          } else {
            // 정규식 통과 실패
            dispatch(
              authAction.setSignupError({
                key: name,
                message: `${formKeyMap[name]}: ${regexErrorMap[name]}`,
              })
            );
          }
        } else {
          // 011, 016, 017, 018, 019로 시작 할 경우
          if (regexMap.phoneNumber2.test(value)) {
            // 정규식 통과 성공
            if (!value.includes("-")) {
              // - 없는 경우
              if (value.length === 11) {
                // 000-0000-0000 인 경우
                dispatch(
                  authAction.changeField({
                    form: "signup",
                    key: name,
                    value: value.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3"),
                  })
                );
              }
              if (value.length === 10) {
                // 000-000-0000 인 경우
                dispatch(
                  authAction.changeField({
                    form: "signup",
                    key: name,
                    value: value.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3"),
                  })
                );
              }
            }
            // 휴대전화번호 중복 체크
            duplicatedCheck(
              name,
              value.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3")
            );
          } else {
            // 정규식 통과 실패
            dispatch(
              authAction.setSignupError({
                key: name,
                message: `${formKeyMap[name]}: ${regexErrorMap[name]}`,
              })
            );
          }
        }
      } else {
        // 이메일, 비밀번호, 닉네임 정규식 검사
        if (regexMap[name].test(value)) {
          // 정규식 통과 성공
          if (name === "email") {
            // 이메일인 경우 중복 체크
            duplicatedCheck(name, value);
          } else {
            dispatch(
              authAction.setSignupError({
                key: name,
                message: "success",
              })
            );
          }
        } else {
          // 정규식 통과 실패
          dispatch(
            authAction.setSignupError({
              key: name,
              message: `${formKeyMap[name]}: ${regexErrorMap[name]}`,
            })
          );
        }
      }
    }
  };

  // 중복 검사 함수
  const duplicatedCheck = (name, value) => {
    dispatch(
      authAction.duplicatedCheck({
        key: name,
        value: value,
      })
    );
  };

  const onSubmit = (e) => {
    e.preventDefault();
    const { email, password, nickname, phoneNumber } = form;

    if (
      error.email === "success" &&
      error.password === "success" &&
      error.nickname === "success" &&
      error.phoneNumber === "success"
    ) {
      dispatch(authAction.signup(form));
    } else {
      if (error.email !== "success") {
        regexCheck("email", email);
      }

      if (error.password !== "success") {
        regexCheck("password", password);
      }

      if (error.nickname !== "success") {
        regexCheck("nickname", nickname);
      }

      if (error.phoneNumber !== "success") {
        regexCheck("phoneNumber", phoneNumber);
      }
    }
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

  // api 요청 성공 / 실패 처리
  useEffect(() => {
    // 이메일 중복 체크 성공
    if (response.code === "DUPLICATED_EMAIL_CHECK_SUCCESSFUL") {
      dispatch(
        authAction.setSignupError({
          key: "email",
          message: "success",
        })
      );
    }

    // 휴대전화번호 중복 체크 성공
    if (response.code === "DUPLICATED_PHONENUMBER_CHECK_SUCCESSFUL") {
      dispatch(
        authAction.setSignupError({
          key: "phoneNumber",
          message: "success",
        })
      );
    }

    // 이메일 중복 체크 실패
    if (response.code === "DUPLICATED_EMAIL") {
      dispatch(
        authAction.setSignupError({
          key: "email",
          message: `${formKeyMap.email}: ${response.message}`,
        })
      );
    }

    // 휴대전화번호 중복 체크 실패
    if (response.code === "DUPLICATED_PHONENUMBER") {
      dispatch(
        authAction.setSignupError({
          key: "phoneNumber",
          message: `${formKeyMap.phoneNumber}: ${response.message}`,
        })
      );
    }

    // 회원가입 성공
    if (response.code === "SIGNUP_SUCCESSFUL") {
      alert("회원가입 성공");
      navigate("/login");

      return () => {
        dispatch(authAction.initializeForm());
      };
    }

    // 회원가입 실패
    if (response.code === "VALIDATION_ERROR") {
      for (let key in response.errors) {
        dispatch(
          authAction.setSignupError({
            key: key,
            message: `${formKeyMap[key]}: ${response.errors[key]}`,
          })
        );
      }
    }
  }, [response, dispatch, navigate]);

  return (
    <SignupForm
      form={form}
      onChange={onChange}
      onBlur={onBlur}
      onSubmit={onSubmit}
      error={error}
    />
  );
};

export default SignupContainer;
