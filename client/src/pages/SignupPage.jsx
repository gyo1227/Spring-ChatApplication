import React from "react";
import { styled } from "styled-components";
import SignupContainer from "../containers/SignupContainer";

const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  background-color: #f5f5f5;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const WhiteBox = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 540px;
  margin: 0 20px;
  padding: 160px 30px 38px;
  border-radius: 1.25rem;
  background: #fff;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
`;

const SignupPage = () => {
  return (
    <Wrapper>
      <WhiteBox>
        <SignupContainer />
      </WhiteBox>
    </Wrapper>
  );
};

export default SignupPage;
