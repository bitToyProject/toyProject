import React from 'react';
import { useRecoilState } from 'recoil';

const Navbar = () => {
  return (
    <>
      <div className="relative flex-1 flex flex-col min-h-0 border-r border-gray-200 bg-white pt-0">
        <div className="flex-1 flex flex-col pt-5 pb-4 overflow-y-auto">
          <div className="flex-1 px-3 bg-white divide-y space-y-1">
            <ul className="space-y-2 pb-2">
              {}
              <li>
                <span className="ml-3">Dashboard</span>
              </li>
              <li>
                <span className="ml-3">Dashboard</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </>
  );
};

export default Navbar;
