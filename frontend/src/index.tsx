import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { RecoilRoot } from "recoil";
import axios from "axios";
import { css } from "@emotion/react";
/** @jsxImportSource @emotion/react */
<<<<<<< HEAD
import './index.css';
axios.defaults.baseURL = 'http://192.168.0.9:8080';
=======
import "./index.css";
axios.defaults.baseURL = "http://192.168.0.9:8080";
>>>>>>> 74665a02616ceec338867c6c7354bd2ee0a02942
// axios.defaults.withCredentials = true;

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <RecoilRoot>
        <App />
      </RecoilRoot>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);
