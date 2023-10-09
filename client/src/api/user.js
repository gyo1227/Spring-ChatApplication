import axios from "axios";

// 토큰 검증
export const getUserInfo = async (token) => {
  const response = await axios.get("/api/member/me", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response;
};
