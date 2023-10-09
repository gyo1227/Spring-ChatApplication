import { createAction } from "redux-actions";
import createRequestActionType from "../../utils/lib/createRequestActionType";

export const SET_ACCESSTOKEN = "user/SET_ACCESSTOKEN";

export const [GET_USER_INFO, GET_USER_INFO_SUCCESS, GET_USER_INFO_FAILURE] =
  createRequestActionType("user/GET_USER_INFO");

export const [REISSUE, REISSUE_SUCCESS, REISSUE_FAILURE] =
  createRequestActionType("user/REISSUE");

export const LOGOUT = "user/logout";

export const setAccessToken = createAction(
  SET_ACCESSTOKEN,
  (accessToken) => accessToken
);

export const getUserInfo = createAction(
  GET_USER_INFO,
  (accessToken) => accessToken
);

export const reissue = createAction(REISSUE);

export const logout = createAction(LOGOUT);
