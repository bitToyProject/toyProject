import { InputModule } from 'src/webapp/common';
import { ColoredButton } from 'src/webapp/container';
import { MouseEvent, useState, useCallback, useEffect } from 'react';
import { useRecoilState, useRecoilValue, useSetRecoilState } from 'recoil';
import {
  emailCheckSelector,
  emailState,
  ISignupType,
  signupSelector,
  signupState,
} from 'src/webapp/recoil/signup/signup';

interface signupValType {
  username: string;
  password: string;
  nickName: string;
  phoneNum: string;
  firstName: string;
  lastName: string;
  gender: number;
}

const SignupPage = () => {
  const [signupVal, setSignupVal] = useState<signupValType>({
    username: '',
    password: '',
    nickName: '',
    phoneNum: '',
    firstName: '',
    lastName: '',
    gender: 0,
  });
  const [disabled, setDisabled] = useState<boolean>(false);
  const [signup, setSignup] = useRecoilState<ISignupType>(signupState);

  const setEmail = useSetRecoilState<String>(emailState);

  const data = useRecoilValue(signupSelector(signup));
  useRecoilValue(emailCheckSelector);

  const handleClick = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();
      setSignup(signupVal);
    },
    [signupVal]
  );

  const handleEmailCheck = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();
      setEmail(signupVal.username);
    },
    [signupVal]
  );

  return (
    <>
      <div className="h-screen flex items-center justify-center bg-gray-100">
        <div className="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-3xl w-6/12 max-w-md">
          <div className="mb-4">
            <label className="block mb-1">이메일</label>
            <div className="flex">
              <InputModule
                type={'text'}
                disabled={false}
                placeholder={'Email'}
                onChange={(value: string) =>
                  setSignupVal({ ...signupVal, username: value })
                }
              />
              <ColoredButton
                disabled={disabled}
                btnLabel={'이메일 중복검사'}
                color={''}
                backgroundColor={''}
                isWhite
                handleClick={handleEmailCheck}
              />
            </div>
          </div>
          <div className="mb-4">
            <label className="block mb-1">닉네임</label>
            <InputModule
              type={'nickName'}
              disabled={false}
              placeholder={'nickName'}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, nickName: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">비밀번호</label>
            <InputModule
              type={'password'}
              disabled={false}
              placeholder={'Password'}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, password: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">핸드폰 번호</label>
            <InputModule
              type={'phoneNum'}
              disabled={false}
              placeholder={'phoneNum'}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, phoneNum: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">성</label>
            <InputModule
              type={'lastName'}
              disabled={false}
              placeholder={'lastName'}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, lastName: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">이름</label>
            <InputModule
              type={'firstName'}
              disabled={false}
              placeholder={'firstName'}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, firstName: value })
              }
            />
          </div>
          <div className="mt-6">
            <ColoredButton
              disabled={disabled}
              btnLabel={'회원가입'}
              color={''}
              backgroundColor={''}
              isWhite
              handleClick={handleClick}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default SignupPage;
