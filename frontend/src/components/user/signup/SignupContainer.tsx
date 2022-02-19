import { MouseEvent, useState, useCallback } from "react";
import { ISignupValType } from "src/types/signupTypes";
import { useAxios } from "src/hooks/useAxios";
import InputModule from "src/components/common/input/InputModule";
import ColoredButton from "src/components/common/button/ColoredButton";

const SignupPage = () => {
  const [signupVal, setSignupVal] = useState<ISignupValType>({
    username: "",
    password: "",
    nickName: "",
    phoneNum: "",
    firstName: "",
    lastName: "",
    gender: 0,
  });
  const { response, error, loading, request } = useAxios({
    method: "post",
    url: "auth/login",
    data: { username: "user1@naver.com", password: "woals1212!" },
    headers: {
      accept: "*/*",
    },
  });

  const handleClick = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();
      request();
    },
    [signupVal]
  );

  const handleEmailCheck = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();
      // setEmail(signupVal.username);
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
                type={"text"}
                disabled={false}
                placeholder={"Email"}
                onChange={(value: string) =>
                  setSignupVal({ ...signupVal, username: value })
                }
              />
              <ColoredButton
                disabled={false}
                btnLabel={"이메일 중복검사"}
                color={""}
                backgroundColor={""}
                isWhite
                handleClick={handleEmailCheck}
              />
            </div>
          </div>
          <div className="mb-4">
            <label className="block mb-1">닉네임</label>
            <InputModule
              type={"nickName"}
              disabled={false}
              placeholder={"nickName"}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, nickName: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">비밀번호</label>
            <InputModule
              type={"password"}
              disabled={false}
              placeholder={"Password"}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, password: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">핸드폰 번호</label>
            <InputModule
              type={"phoneNum"}
              disabled={false}
              placeholder={"phoneNum"}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, phoneNum: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">성</label>
            <InputModule
              type={"lastName"}
              disabled={false}
              placeholder={"lastName"}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, lastName: value })
              }
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1">이름</label>
            <InputModule
              type={"firstName"}
              disabled={false}
              placeholder={"firstName"}
              onChange={(value: string) =>
                setSignupVal({ ...signupVal, firstName: value })
              }
            />
          </div>
          <div className="mt-6">
            <ColoredButton
              disabled={false}
              btnLabel={"회원가입"}
              color={""}
              backgroundColor={""}
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
