import { InputModule } from "@/webapp/common";
import { ColoredButton } from "@/webapp/container";
import { ISignupType, signupState } from "@/webapp/recoil/signup/signup";
import { css } from "@emotion/react";
import React, { MouseEvent, useState } from "react";
import { useRecoilValue, useSetRecoilState } from "recoil";

interface signupValType {
  username: string;
  password: string;
  nickname: string;
}

const SignupPage = () => {
  const [signupVal, setSignupVal] = useState<signupValType>({
    username: "",
    password: "",
    nickname: "",
  });
  const [disabled, setDisabled] = useState<boolean>(false);
  const signupInfo = useRecoilValue<ISignupType>(signupState); // atom getter
  const setSignupInfo = useSetRecoilState<ISignupType>(signupState); // atom setter

  const handleClick = (
    e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    e.preventDefault();
    e.stopPropagation();
    setSignupInfo(signupVal);
  };

  return (
    <>
      <label>이메일</label>
      <InputModule
        type={"text"}
        disabled={false}
        placeholder={"Email"}
        onChange={(value: string) =>
          setSignupVal({ ...signupVal, username: value })
        }
      />
      <label>비밀번호</label>
      <InputModule
        type={"password"}
        disabled={false}
        placeholder={"Password"}
        onChange={(value: string) =>
          setSignupVal({ ...signupVal, password: value })
        }
      />
      <label>닉네임</label>
      <InputModule
        type={"text"}
        disabled={false}
        placeholder={"Nickname"}
        onChange={(value: string) =>
          setSignupVal({ ...signupVal, nickname: value })
        }
      />
      <ColoredButton
        disabled={disabled}
        btnLabel={"회원가입"}
        color={""}
        backgroundColor={""}
        isWhite
        handleClick={handleClick}
      />
    </>
  );
};

export default SignupPage;
