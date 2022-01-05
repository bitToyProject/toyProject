import { InputModule } from 'src/webapp/common';
import React, { useState, ChangeEvent, MouseEvent } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import {
  apiLogin,
  ILoginType,
  IResStatus,
  loginState,
} from 'src/webapp/recoil/login/login';
import { ColoredButton } from 'src/webapp/container';
import { useNavigate } from 'react-router';

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

  const navigate = useNavigate();

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
      <div className="w-full min-h-screen bg-gray-50 flex flex-col sm:justify-center items-center pt-6 sm:pt-0">
        <div className="w-full sm:max-w-md p-5 mx-auto">
          <h2 className="mb-12 text-center text-5xl font-extrabold">
            Welcome.
          </h2>
          <div className="mb-4">
            <label className="block mb-1">이메일:</label>
            <InputModule
              type={'text'}
              disabled={false}
              placeholder={'Email'}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, email: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">비밀번호:</label>
            <InputModule
              type={'password'}
              disabled={false}
              placeholder={'Password'}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, password: value })
              }
            />
          </div>
          <div className="mt-6">
            <ColoredButton
              disabled={disabled}
              btnLabel={'로그인'}
              color={''}
              backgroundColor={''}
              isWhite
              handleClick={handleClick}
            />
          </div>
          <div className="mt-6 text-center">
            <p className="text-sm">비밀번호 찾기</p>
            <p
              className="text-sm"
              onClick={() => {
                navigate('/member/signup');
              }}
            >
              회원가입
            </p>
          </div>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
