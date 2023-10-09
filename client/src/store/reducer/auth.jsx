import { handleActions } from "redux-actions";
import {
  CHANGE_FIELD,
  DUPLICATED_CHECK_FAILURE,
  DUPLICATED_CHECK_SUCCESS,
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
  response: {
    code: null,
    status: null,
    message: null,
  },
};

const auth = handleActions(
  {
    // form 초기화
    [INITIALIZE_FORM]: (state) => ({
      ...state,
      form: initialState.form,
      error: initialState.error,
      response: initialState.response,
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
    // 중복 체크 성공
    [DUPLICATED_CHECK_SUCCESS]: (state, { payload: response }) => ({
      ...state,
      response: {
        code: response.code,
        status: response.status,
        message: response.message,
      },
    }),

    // 중복 체크 실패
    [DUPLICATED_CHECK_FAILURE]: (state, { payload: error }) => ({
      ...state,
      response: {
        code: error.response.data.code,
        status: error.response.data.status,
        message: error.response.data.message,
      },
    }),
    // 회원가입 성공
    [SIGNUP_SUCCESS]: (state, { payload: response }) => ({
      ...state,
      response: {
        code: response.code,
        status: response.status,
        message: response.message,
      },
    }),
    // 회원가입 실패
    [SIGNUP_FAILURE]: (state, { payload: error }) => ({
      ...state,
      response: {
        code: error.response.data.code,
        status: error.response.data.status,
        message: error.response.data.message,
        errors: error.response.data.errors,
      },
    }),
    // 로그인 성공
    [LOGIN_SUCCESS]: (state, { payload: response }) => ({
      ...state,
      response: {
        code: response.code,
        status: response.status,
        message: response.message,
        data: response.data,
      },
    }),
    // 로그인 실패
    [LOGIN_FAILURE]: (state, { payload: error }) => ({
      ...state,
      response: {
        code: error.response.data.code,
        status: error.response.data.status,
        message: error.response.data.message,
      },
    }),
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
