import { InputModule } from "src/webapp/common";
import React, { useState, ChangeEvent, MouseEvent } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import {
  apiLogin,
  ILoginType,
  IResStatus,
  loginState,
} from "src/webapp/recoil/login/login";
import { ColoredButton } from "src/webapp/container";
import { css } from "@emotion/react";
import {
  COLOR_WHITE,
  DEFAULT_BACKGROUND,
} from "src/webapp/common/CCstyle/CCstyle";
/** srcjsxImportSource srcemotion/react */

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

  // const apiLoginCall = useRecoilValue(apiLogin(loginInfo));
  // console.log(apiLoginCall);
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
      <div className="h-screen grid grid-cols-3">
        <div className="col-span-2 bg-orange-300">
          <h1 className="justify-center text-center font-bold py-60">Bora</h1>
        </div>
        <div className="">
          <form className="jtext-center py-60">
            <h1 className="text-center">로그인</h1>
            <div className="">
              <label className="mb-1 text-xs tracking-wide text-gray-600">
                이메일:
              </label>
              <input className="text-sm pr-4 w-full py-2 focus:outline-none focus:border-blue-400" />
            </div>
            <div>
              <label className="mb-1 text-xs tracking-wide text-gray-600">
                비밀번호:
              </label>
              <input className="text-sm placeholder-gray-500 pl-10 pr-4 w-full py-2 focus:outline-none focus:border-blue-400" />
            </div>
            <button className="block w-full bg-indigo-600 mt-4 py-2 rounded-2xl text-white font-semibold mb-2">
              로그인
            </button>
          </form>
          <p className="">회원가입</p>
        </div>
      </div>
      <div css={cssWrapper}>
        <div className="inline-block-box">
          <label>이메일</label>
          <div>
            <InputModule
              type={"text"}
              disabled={false}
              placeholder={"Email"}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, email: value })
              }
            />
          </div>
          <label>비밀번호</label>
          <div>
            <InputModule
              type={"password"}
              disabled={false}
              placeholder={"Password"}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, password: value })
              }
            />
          </div>
          <div className="btn-box">
            <ColoredButton
              disabled={disabled}
              btnLabel={"로그인하기"}
              color={""}
              backgroundColor={""}
              isWhite
              handleClick={handleClick}
            />
            <ColoredButton
              disabled={disabled}
              btnLabel={"회원가입하기"}
              color={""}
              backgroundColor={""}
              isWhite
              handleClick={() => {}}
            />
          </div>
        </div>
        <div
          css={css`
            width: 10px;
            height: 10px;
          `}
        ></div>
      </div>
    </>
  );
};

export default LoginPage;
const cssWrapper = css`
  width: 85%;
  border: 0.5rem solid ${COLOR_WHITE};
  min-width: 600px;
  max-width: 1650px;
  min-height: 50vh;
  max-height: 65vh;
  margin: 2rem auto;
  .inline-block-box {
    width: 50%;
    height: 200px;
    margin: 0 auto;
    input {
      max-width: 100%;
      min-width: 100%;
      min-height: 5vh;
      max-height: 5vh;
      box-sizing: border-box;
      border: 0.3rem solid ${COLOR_WHITE};
      border-radius: 1px;
      background-color: ${DEFAULT_BACKGROUND};
    }
    .btn-box {
      margin-top: 3rem;
      display: flex;
      justify-content: end;
      box-sizing: border-box;
      border: 1px;
    }
  }
`;
