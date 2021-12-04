import { InputModule } from '@/webapp/common';
import { COLOR_WHITE } from '@/webapp/common/CCstyle/CCstyle';
import { checkNull } from '@/webapp/config/regExp/RegExp';
import { ColoredButton } from '@/webapp/container';
import { signupState } from '@/webapp/recoil/atom';
import { signupSelector } from '@/webapp/recoil/seletors';
import { ISignupType } from '@/webapp/recoil/types';
import { css } from '@emotion/react';
/** @jsxImportSource @emotion/react */
import React, { MouseEvent, useState, useCallback, useEffect } from 'react';
import { useHistory } from 'react-router';
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
  console.log(data);

  return (
    <>
      <div
        css={css`
          height: 100%;
          backgroud-color: red;
          padding: 5px;
          border-radius: 2px;
          display: flex;
        `}
      >
        <label>이메일</label>
        <InputModule
          type={'text'}
          disabled={false}
          placeholder={'Email'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, username: value })
          }
        />
        <label>닉네임</label>
        <InputModule
          type={'nickName'}
          disabled={false}
          placeholder={'nickName'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, nickName: value })
          }
        />
        <label>비밀번호</label>
        <InputModule
          type={'password'}
          disabled={false}
          placeholder={'Password'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, password: value })
          }
        />

        <label>핸드폰 번호</label>
        <InputModule
          type={'phoneNum'}
          disabled={false}
          placeholder={'phoneNum'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, phoneNum: value })
          }
        />
        <label>성</label>
        <InputModule
          type={'lastName'}
          disabled={false}
          placeholder={'lastName'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, lastName: value })
          }
        />
        <label>이름</label>
        <InputModule
          type={'firstName'}
          disabled={false}
          placeholder={'firstName'}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, firstName: value })
          }
        />
        <label>성별</label>

        <ColoredButton
          disabled={disabled}
          btnLabel={'회원가입'}
          color={''}
          backgroundColor={''}
          isWhite
          handleClick={handleClick}
        />
      </div>
    </>
  );
};

export default SignupPage;

const cssWrapper = css`
  border: 0.15rem solid ${COLOR_WHITE};
  width: 80%;
  margin: 0 auto;
`;
