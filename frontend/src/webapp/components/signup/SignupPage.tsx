import { InputModule } from '@/webapp/common';
import { ColoredButton } from '@/webapp/container';
import { signupState } from '@/webapp/recoil/atom';
import { signupSelector } from '@/webapp/recoil/seletors';
import { ISignupType } from '@/webapp/recoil/type';
import React, { MouseEvent, useState, useCallback, useEffect } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';

interface signupValType {
    email: string;
    password: string;
}

const SignupPage = () => {
    const [signupVal, setSignupVal] = useState<signupValType>({
        email: '',
        password: '',
    });
    const [disabled, setDisabled] = useState<boolean>(false);
    const [signup, setSignup] = useRecoilState<ISignupType>(signupState);

    const data = useRecoilValue(signupSelector(signup));

    const handleClick = useCallback(
        (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
            e.preventDefault();
            e.stopPropagation();
            setSignup(signupVal);

            if (data.result === 'SUCCESS') {
                alert('회원가입 되었습니다.');
            } else {
                return false;
            }
        },
        []
    );

    return (
        <>
            <label>이메일</label>
            <InputModule
                type={'text'}
                disabled={false}
                placeholder={'Email'}
                onChange={(value: string) =>
                    setSignupVal({ ...signupVal, email: value })
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
            <ColoredButton
                disabled={disabled}
                btnLabel={'회원가입'}
                color={''}
                backgroundColor={''}
                isWhite
                handleClick={handleClick}
            />
        </>
    );
};

export default SignupPage;
