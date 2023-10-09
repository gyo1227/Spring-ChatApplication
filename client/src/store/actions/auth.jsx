import { createAction } from "redux-actions";
import createRequestActionType from "../../utils/lib/createRequestActionType";

export const INITIALIZE_FORM = "auth/INITIALIZE_FORM";

export const CHANGE_FIELD = "auth/CHANGE_FIELD";

export const [
  DUPLICATED_CHECK,
  DUPLICATED_CHECK_SUCCESS,
  DUPLICATED_CHECK_FAILURE,
] = createRequestActionType("auth/DUPLICATED_CHECK");

export const [SIGNUP, SIGNUP_SUCCESS, SIGNUP_FAILURE] =
  createRequestActionType("auth/SIGNUP");

export const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] =
  createRequestActionType("auth/LOGIN");

export const SET_SIGNUP_ERROR = "auth/SET_SIGNUP_ERROR";

export const SET_LOGIN_ERROR = "auth/SET_LOGIN_ERROR";

export const initializeForm = createAction(INITIALIZE_FORM);

export const changeField = createAction(
  CHANGE_FIELD,
  ({ form, key, value }) => ({
    form,
    key,
    value,
  })
);

export const duplicatedCheck = createAction(
  DUPLICATED_CHECK,
  ({ key, value }) => ({
    key,
    value,
  })
);

export const signup = createAction(
  SIGNUP,
  ({ email, password, nickname, phoneNumber }) => ({
    email,
    password,
    nickname,
    phoneNumber,
  })
);

export const login = createAction(LOGIN, ({ email, password }) => ({
  email,
  password,
}));

export const setSignupError = createAction(
  SET_SIGNUP_ERROR,
  ({ key, message }) => ({
    key,
    message,
  })
);

export const setLoginError = createAction(SET_LOGIN_ERROR, ({ message }) => ({
  message,
}));
