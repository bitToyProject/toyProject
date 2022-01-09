import React from 'react';

const Loading = () => {
  return (
    <>
      <div className="h-screen bg-white">
        <div className="flex flex-col justify-center items-center h-full">
          <img
            className="h-16 w-16"
            src="https://icons8.com/preloaders/preloaders/1488/Iphone-spinner-2.gif"
            alt="LOADING"
          />
          <br />
          <p>LOADING.....😭</p>
        </div>
      </div>
    </>
  );
};

export default Loading;
