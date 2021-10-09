import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { RecoilRoot } from "recoil";
import axios from "axios";
import { css } from "@emotion/react";
/** @jsxImportSource @emotion/react */
import "./index.css";
axios.defaults.baseURL = "http://172.16.101.44:8080";
// axios.defaults.withCredentials = true;

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <RecoilRoot>
        <div
          css={css`
            background-color: black;
            width: 100%;
            height: 100%;
          `}
        >
          <App />
        </div>
      </RecoilRoot>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);
