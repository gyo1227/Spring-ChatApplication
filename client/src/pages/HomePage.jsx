import React from "react";
import { PAGE_PATH } from "../utils/constants";
import styled from "styled-components";
import { Link } from "react-router-dom";

const Button = styled(Link)`
  cursor: pointer;
  width: 100%;
  height: 50px;
  background: #216ce7;
  color: #fff;
  padding: 0 16px;
  margin-top: 14px;
  border: none;
  border-radius: 24px;
  text-align: center;
  font-size: 1rem;
  font-weight: 600;
  letter-spacing: 2px;
  transition: all 0.375s;

  &:hover {
    background-color: #143d81;
  }
`;

const HomePage = () => {
  return <Button to={PAGE_PATH.LOGIN}>Start</Button>;
};

export default HomePage;
