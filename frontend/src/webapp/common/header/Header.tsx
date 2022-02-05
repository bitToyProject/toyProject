import React from 'react';

const Header = () => {
  return (
    <>
      <nav className="bg-white border-b border-gray-200 fixed z-30 w-full">
        <div className="px-3 py-3 lg:px-5 lg:pl-3">
          <div className="flex items-center justify-between">
            <div className="flex items-center justify-start">
              <span className="self-center whitespace-nowrap">
                <img
                  src={process.env.PUBLIC_URL + '/logo/BORA-logo.png'}
                  width="80"
                  alt="BORA"
                />
              </span>
            </div>
            <div className="flex items-center">
              <div className="hidden lg:flex items-center">
                <span className="text-base font-normal text-gray-500 mr-5">
                  회원가입
                </span>
                <span className="text-base font-normal text-gray-500 mr-5">
                  로그인
                </span>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Header;
