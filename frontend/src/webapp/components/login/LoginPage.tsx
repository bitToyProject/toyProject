import { InputModule } from 'src/webapp/common';
import React, { useState, MouseEvent } from 'react';
import { ColoredButton } from 'src/webapp/container';
import { ILoginValType } from 'src/webapp/types/loginTypes';
import { useMutation } from 'react-query';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { setCookies } from 'src/webapp/config/cookie/Cookie';

const LoginPage = () => {
  const [loginVal, setLoginVal] = useState<ILoginValType>({
    username: '',
    password: '',
  });
  const [disabled, setDisabled] = useState<boolean>(false);

  const navigate = useNavigate();

  const doLogin = useMutation((data: ILoginValType) =>
    axios.post('/auth/login', data)
  );

  const handleClick = (
    e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    // e.preventDefault();
    e.stopPropagation();

    doLogin.mutate(loginVal, {
      onSuccess: async (response) => {
        const accessToken = await response.data.data.accessToken;
        setCookies('loginToken', accessToken, {
          path: '/',
          // httpOnly: true,
          maxAge: 60 * 60 * 24 * 7,
        });
        navigate('/task/todo');
      },
      onError: (err) => {},
    });
  };
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
              type={"text"}
              disabled={false}
              placeholder={"Email"}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, username: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">비밀번호:</label>
            <InputModule
              type={"password"}
              disabled={false}
              placeholder={"Password"}
              onChange={(value: string) =>
                setLoginVal({ ...loginVal, password: value })
              }
            />
          </div>
          <div className="mt-6">
            <ColoredButton
              disabled={disabled}
              btnLabel={"로그인"}
              color={""}
              backgroundColor={""}
              isWhite
              handleClick={handleClick}
            />
          </div>
          <div className="mt-6 text-center">
            <p>
              <Link to="/member/findPassword" className="text-sm">
                {' '}
                비밀번호 찾기
              </Link>
            </p>
            <p>
              <Link to="/member/signup" className="text-sm">
                회원가입
              </Link>
            </p>
          </div>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
