import { handleActions } from "redux-actions";
import {
  CHANGE_FIELD,
  INITIALIZE_FORM,
  LOGIN_FAILURE,
  LOGIN_SUCCESS,
  SET_LOGIN_ERROR,
  SET_SIGNUP_ERROR,
  SIGNUP_FAILURE,
  SIGNUP_SUCCESS,
} from "../actions/auth";

const initialState = {
  form: {
    signup: {
      email: "",
      password: "",
      nickname: "",
      phoneNumber: "",
    },
    login: {
      email: "",
      password: "",
    },
  },
  error: {
    signup: {
      email: null,
      password: null,
      nickname: null,
      phoneNumber: null,
    },
    login: null,
  },
};

const auth = handleActions(
  {
    // form 초기화
    [INITIALIZE_FORM]: (state) => ({
      ...state,
      form: initialState.form,
    }),
    // input 값 변경
    [CHANGE_FIELD]: (state, { payload: { form, key, value } }) => ({
      ...state,
      form: {
        ...state.form,
        [form]: {
          ...state.form[form],
          [key]: value,
        },
      },
    }),
    // 회원가입 성공
    [SIGNUP_SUCCESS]: () => ({}),
    // 회원가입 실패
    [SIGNUP_FAILURE]: () => ({}),
    // 로그인 성공
    [LOGIN_SUCCESS]: () => ({}),
    // 로그인 실패
    [LOGIN_FAILURE]: () => ({}),
    [SET_SIGNUP_ERROR]: (state, { payload: { key, message } }) => ({
      ...state,
      error: {
        ...state.error,
        signup: {
          ...state.error.signup,
          [key]: message,
        },
      },
    }),
    [SET_LOGIN_ERROR]: (state, { payload: { message } }) => ({
      ...state,
      error: {
        ...state.error,
        login: {
          message,
        },
      },
    }),
  },
  initialState
);

export default auth;
