import createRequestSaga from "../../utils/lib/createRequestSaga";
import { DUPLICATED_CHECK, LOGIN, SIGNUP } from "../actions/auth";
import * as authApi from "../../api/auth";
import { takeLatest } from "redux-saga/effects";

const duplicatedCheckSaga = createRequestSaga(
  DUPLICATED_CHECK,
  authApi.duplicatedCheck
);
const signupSaga = createRequestSaga(SIGNUP, authApi.signup);
const loginSaga = createRequestSaga(LOGIN, authApi.login);

export function* authSaga() {
  yield takeLatest(DUPLICATED_CHECK, duplicatedCheckSaga);
  yield takeLatest(SIGNUP, signupSaga);
  yield takeLatest(LOGIN, loginSaga);
}
