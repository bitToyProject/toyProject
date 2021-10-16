import { Route } from "react-router";
import { LoginApp, SignupApp } from "@/webapp/container";
import { Suspense } from "react";
import Loading from "./webapp/common/Loading";
import { css } from "@emotion/react";
const App = () => {
  return (
    <>
      {/* <Suspense fallback={<Loading />}> */}
      <Route exact path="/" component={LoginApp}></Route>
      {/* 회원가입 */}
      <Route exact path={"/member/signup"} component={SignupApp}></Route>
      {/* recoil test todoList */}
      {/* </Suspense> */}
    </>
  );
};

export default App;
