import React, { useState } from 'react';
import { InputModule } from 'src/webapp/common';
import { ColoredButton } from 'src/webapp/container';

const FindPasswordPage = () => {
  const [disabled, setDisabled] = useState<boolean>(false);
  const handleClick = () => {};
  return (
    <>
      <div className="w-full min-h-screen bg-gray-50 flex flex-col sm:justify-center items-center pt-6 sm:pt-0">
        <div className="w-full sm:max-w-md p-5 mx-auto">
          <h2 className="mb-12 text-center text-5xl font-extrabold">
            비밀번호 찾기
          </h2>
          <div className="mb-4">
            <label className="block mb-1">휴대전화 번호:</label>
            <div className="flex">
              <InputModule
                type={'text'}
                disabled={false}
                placeholder={'Phone'}
                onChange={(value: string) => ''}
              />
              <ColoredButton
                disabled={disabled}
                btnLabel={'비밀번호 찾기'}
                color={''}
                backgroundColor={''}
                isWhite
                handleClick={handleClick}
              />
            </div>
          </div>
          <div className="mb-4">
            <label className="block mb-1">인증번호:</label>
            <InputModule
              type={'text'}
              disabled={false}
              placeholder={'인증번호'}
              onChange={(value: string) => ''}
            />
          </div>
          <div className="mt-6">
            <ColoredButton
              disabled={disabled}
              btnLabel={'비밀번호 찾기'}
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

export default FindPasswordPage;
