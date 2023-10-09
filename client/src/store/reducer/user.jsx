import { handleActions } from "redux-actions";
import {
  GET_USER_INFO_FAILURE,
  GET_USER_INFO_SUCCESS,
  LOGOUT,
  REISSUE_FAILURE,
  REISSUE_SUCCESS,
  SET_ACCESSTOKEN,
} from "../actions/user";

const initialState = {
  info: {
    accessToken: null,
    nickname: null,
    imageUrl: null,
  },
};

const user = handleActions(
  {
    [SET_ACCESSTOKEN]: (state, { payload: accessToken }) => ({
      ...state,
      info: {
        ...state.info,
        accessToken: accessToken,
      },
    }),
    [GET_USER_INFO_SUCCESS]: (state, { payload: response }) => ({
      ...state,
    }),
    [GET_USER_INFO_FAILURE]: (state, { payload: error }) => ({
      ...state,
    }),
    [REISSUE_SUCCESS]: (state, { payload: response }) => ({
      ...state,
    }),
    [REISSUE_FAILURE]: (state, { payload: error }) => ({
      ...state,
    }),
    [LOGOUT]: (state) => ({
      ...state,
    }),
  },
  initialState
);

export default user;
