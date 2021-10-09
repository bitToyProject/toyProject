import { InputModule } from "@/webapp/common";
import React, { useState, ChangeEvent, MouseEvent } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  apiLogin,
  ILoginType,
  IResStatus,
  loginState,
} from "@/webapp/recoil/login/login";
import { ColoredButton } from "@/webapp/container";

interface loginValType {
  email: string;
  password: string;
}
const LoginPage = () => {
  const [loginVal, setLoginVal] = useState<loginValType>({
    email: "",
    password: "",
  });
  const [disabled, setDisabled] = useState<boolean>(false);
  const [loginInfo, setLoginInfo] = useRecoilState<ILoginType>(loginState);
  const apiLoginCall = useRecoilValue(apiLogin(loginInfo));
  console.log(apiLoginCall);
  const handleClick = (
    e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    e.preventDefault();
    e.stopPropagation();
    setLoginInfo(loginVal);
  };

  console.log("loginVal", loginVal);
  return (
    <>
      <label>이메일</label>
      <InputModule
        type={"text"}
        disabled={false}
        placeholder={"Email"}
        onChange={(value: string) => setLoginVal({ ...loginVal, email: value })}
      />
      <label>비밀번호</label>
      <InputModule
        type={"password"}
        disabled={false}
        placeholder={"Password"}
        onChange={(value: string) =>
          setLoginVal({ ...loginVal, password: value })
        }
      />
      <ColoredButton
        disabled={disabled}
        btnLabel={"로그인하기"}
        color={""}
        backgroundColor={""}
        isWhite
        handleClick={handleClick}
      />
    </>
  );
};

export default LoginPage;
