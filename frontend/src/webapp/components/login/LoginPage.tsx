import { InputModule } from "@/webapp/common";
import React, { useState, ChangeEvent, MouseEvent } from "react";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { ILoginType, loginState } from "@/webapp/recoil/login/login";
import { ColoredButton } from "@/webapp/container";

interface loginValType {
  username: string;
  password: string;
}
const LoginPage = () => {
  const [loginVal, setLoginVal] = useState<loginValType>({
    username: "",
    password: "",
  });
  const [disabled, setDisabled] = useState<boolean>(false);
  const loginInfo = useRecoilValue<ILoginType>(loginState);
  const setLoginInfo = useSetRecoilState<ILoginType>(loginState);

  const handleClick = (
    e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    e.preventDefault();
    e.stopPropagation();
    setLoginInfo(loginVal);
  };

  console.log("lgoinInfo", loginInfo);
  console.log("loginVal", loginVal);
  return (
    <>
      <label>이메일</label>
      <InputModule
        type={"text"}
        disabled={false}
        placeholder={"Email"}
        onChange={(value: string) =>
          setLoginVal({ ...loginVal, username: value })
        }
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
