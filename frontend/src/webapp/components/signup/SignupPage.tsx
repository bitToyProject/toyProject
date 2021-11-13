<<<<<<< HEAD
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
=======
import { InputModule } from "@/webapp/common";
import { COLOR_WHITE } from "@/webapp/common/CCstyle/CCstyle";
import { ColoredButton } from "@/webapp/container";
import { signupState } from "@/webapp/recoil/atom";
import { signupSelector } from "@/webapp/recoil/seletors";
import { ISignupType } from "@/webapp/recoil/types";
import { css } from "@emotion/react";
/** @jsxImportSource @emotion/react */
import React, {
  MouseEvent,
  useState,
  useCallback,
  useEffect,
  useMemo,
} from "react";
import { useRecoilState, useRecoilValue } from "recoil";
>>>>>>> 74665a02616ceec338867c6c7354bd2ee0a02942

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
<<<<<<< HEAD
    const [signupVal, setSignupVal] = useState<signupValType>({
        username: '',
        password: '',
        nickName: '',
        phoneNum: '',
        firstName: '',
        lastName: '',
        gender: 0,
    });
    const [disabled, setDisabled] = useState<boolean>(true);
    const [signup, setSignup] = useRecoilState<ISignupType>(signupState);

    const data = useRecoilValue(signupSelector(signup));
    const history = useHistory();

    useEffect(() => {
        if (data?.result === 'SUCCESS') {
            alert('회원가입 되었습니다.');
            history.replace('/member/mail_auth');
        }
        // mount 될 경우
        return () => {};
    }, [data]);

    // 모든 input이 null값이 아닌 경우 disabled false로 변경하기(수정하기)
    useEffect(() => {
        if (
            !checkNull([
                signupVal.firstName,
                signupVal.gender,
                signupVal.lastName,
                signupVal.nickName,
                signupVal.password,
                signupVal.phoneNum,
                signupVal.username,
            ])
        )
            setDisabled(false);
    }, [signupVal, disabled]);

    const handleClick = useCallback(
        (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
            e.preventDefault();
            e.stopPropagation();
            setSignup(signupVal);
        },
        [signupVal]
    );

    return (
        <>
            <div css={cssWrapper}>
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
=======
  const [signupVal, setSignupVal] = useState<signupValType>({
    username: "",
    password: "",
    nickName: "",
    phoneNum: "",
    firstName: "",
    lastName: "",
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
      <div css={cssWrapper}>
        <label>이메일</label>
        <InputModule
          type={"text"}
          disabled={false}
          placeholder={"Email"}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, username: value })
          }
        />
        <label>닉네임</label>
        <InputModule
          type={"nickName"}
          disabled={false}
          placeholder={"nickName"}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, nickName: value })
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

        <label>핸드폰 번호</label>
        <InputModule
          type={"phoneNum"}
          disabled={false}
          placeholder={"phoneNum"}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, phoneNum: value })
          }
        />
        <label>성</label>
        <InputModule
          type={"lastName"}
          disabled={false}
          placeholder={"lastName"}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, lastName: value })
          }
        />
        <label>이름</label>
        <InputModule
          type={"firstName"}
          disabled={false}
          placeholder={"firstName"}
          onChange={(value: string) =>
            setSignupVal({ ...signupVal, firstName: value })
          }
        />
        <label>성별</label>

        <ColoredButton
          disabled={disabled}
          btnLabel={"회원가입"}
          color={""}
          backgroundColor={""}
          isWhite
          handleClick={handleClick}
        />
      </div>
    </>
  );
>>>>>>> 74665a02616ceec338867c6c7354bd2ee0a02942
};

export default SignupPage;

const cssWrapper = css`
  border: 0.15rem solid ${COLOR_WHITE};
  width: 80%;
  margin: 0 auto;
`;
