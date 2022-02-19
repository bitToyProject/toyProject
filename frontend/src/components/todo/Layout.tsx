import React from 'react';

const Layout = ({ children, title }: any) => {
  return (
    <>
      <div className="sm:px-6 w-full pt-40">
        <div className="flex items-center justify-between">
          <p className="focus:outline-none text-base sm:text-lg md:text-xl lg:text-2xl font-bold leading-normal text-gray-800">
            {title}
          </p>
        </div>
        {children}
      </div>
    </>
  );
};

export default Layout;
