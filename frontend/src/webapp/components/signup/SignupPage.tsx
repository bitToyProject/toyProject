import { InputModule } from 'src/webapp/common';
import { checkNull } from 'src/webapp/config/regExp/RegExp';
import { ColoredButton } from 'src/webapp/container';
import { signupState } from 'src/webapp/recoil/atom';
import { emailCheckSelector, signupSelector } from 'src/webapp/recoil/seletors';
import { ISignupType } from 'src/webapp/recoil/types';
import { MouseEvent, useState, useCallback, useEffect } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';

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

  const data = useRecoilValue(signupSelector(signup));

  console.log(signupVal);

  const handleClick = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();
      setSignup(signupVal);
    },
    [signupVal]
  );

  const handleEmailCheck = (
    e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    e.preventDefault();
    e.stopPropagation();
    console.log(signupVal.username);
  };

  console.log(data);

  return (
    <>
      <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
        <div className="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-3xl w-6/12 max-w-md">
          <label className="mb-1 text-xs tracking-wide text-gray-600">
            이메일
          </label>
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
          <label className="mb-1 text-xs tracking-wide text-gray-600">
            닉네임
          </label>
          <InputModule
            type={'nickName'}
            disabled={false}
            placeholder={'nickName'}
            onChange={(value: string) =>
              setSignupVal({ ...signupVal, nickName: value })
            }
          />
          <label className="mb-1 text-xs tracking-wide text-gray-600">
            비밀번호
          </label>
          <InputModule
            type={'password'}
            disabled={false}
            placeholder={'Password'}
            onChange={(value: string) =>
              setSignupVal({ ...signupVal, password: value })
            }
          />

          <label className="mb-1 text-xs tracking-wide text-gray-600">
            핸드폰 번호
          </label>
          <InputModule
            type={'phoneNum'}
            disabled={false}
            placeholder={'phoneNum'}
            onChange={(value: string) =>
              setSignupVal({ ...signupVal, phoneNum: value })
            }
          />
          <label className="mb-1 text-xs tracking-wide text-gray-600">성</label>
          <InputModule
            type={'lastName'}
            disabled={false}
            placeholder={'lastName'}
            onChange={(value: string) =>
              setSignupVal({ ...signupVal, lastName: value })
            }
          />
          <label className="mb-1 text-xs tracking-wide text-gray-600">
            이름
          </label>
          <InputModule
            type={'firstName'}
            disabled={false}
            placeholder={'firstName'}
            onChange={(value: string) =>
              setSignupVal({ ...signupVal, firstName: value })
            }
          />
          <label className="mb-1 text-xs tracking-wide text-gray-600">
            성별
          </label>

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
    </>
  );
};

export default SignupPage;
