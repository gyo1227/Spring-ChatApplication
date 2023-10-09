import axios from "axios";

// 중복 체크
export const duplicatedCheck = async (duplicatedCheckData) => {
  const response = await axios.get(`
  /api/auth/check/duplicated?key=${duplicatedCheckData.key}&value=${duplicatedCheckData.value}`);
  return response;
};

// 회원가입
export const signup = async (signupData) => {
  const response = await axios.post("/api/auth/signup", signupData);
  return response;
};

// 로그인
export const login = async (loginData) => {
  const response = await axios.post("/api/auth/login", loginData);
  return response;
};

// 로그아웃
export const logout = () => axios.post("/api/auth/logout");
