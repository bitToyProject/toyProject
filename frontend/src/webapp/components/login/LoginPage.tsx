import { InputModule } from '@/webapp/common';
import React, { useState, ChangeEvent, MouseEvent } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import {
  apiLogin,
  ILoginType,
  IResStatus,
  loginState,
} from '@/webapp/recoil/login/login';
import { ColoredButton } from '@/webapp/container';
import { css } from '@emotion/react';
import {
  COLOR_WHITE,
  DEFAULT_BACKGROUND,
} from '@/webapp/common/CCstyle/CCstyle';
/** @jsxImportSource @emotion/react */

interface loginValType {
  email: string;
  password: string;
}
const LoginPage = () => {
  const [loginVal, setLoginVal] = useState<loginValType>({
    email: '',
    password: '',
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

  console.log('loginVal', loginVal);
  return (
    <>
      <div css={cssWrapper}>
        <div className="inline-block-box">
          <label>이메일</label>
          <div>
            <InputModule
              type={'text'}
              disabled={false}
              placeholder={'Email'}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, email: value })
              }
            />
          </div>
          <label>비밀번호</label>
          <div>
            <InputModule
              type={'password'}
              disabled={false}
              placeholder={'Password'}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, password: value })
              }
            />
          </div>
          <div className="btn-box">
            <ColoredButton
              disabled={disabled}
              btnLabel={'로그인하기'}
              color={''}
              backgroundColor={''}
              isWhite
              handleClick={handleClick}
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
