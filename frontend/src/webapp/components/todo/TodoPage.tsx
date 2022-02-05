import React from 'react';

const TodoPage = () => {
  return (
    <>
      {/* 다음에 할 일 트윌윈드 재사용 가능하게 변경하기 */}
      <div className="mt-7 overflow-x-auto">
        <table className="w-full whitespace-nowrap">
          <tbody>
            <tr className="focus:outline-none h-16 border border-gray-100 rounded">
              <td>
                <div className="ml-5">
                  <div className="bg-gray-200 rounded-sm w-5 h-5 flex flex-shrink-0 justify-center items-center relative">
                    <input
                      placeholder="checkbox"
                      type="checkbox"
                      className="focus:opacity-100 checkbox opacity-0 absolute cursor-pointer w-full h-full"
                    />
                    <div className="check-icon hidden bg-indigo-700 text-white rounded-sm"></div>
                  </div>
                </div>
              </td>
              <td className="">
                <div className="flex items-center pl-5">
                  <p className="text-base font-medium leading-none text-gray-700 mr-2">
                    해야 할일 입력된 모습
                  </p>
                </div>
              </td>
              <td className="pl-5">
                <div className="flex items-center">
                  <div>🔥</div>
                  <p className="text-sm leading-none text-gray-600 ml-2">
                    Urgent
                  </p>
                </div>
              </td>
              <td className="pl-5">
                <div className="flex items-center">
                  <div>⌛️</div>
                  <p className="text-sm leading-none text-gray-600 ml-2">
                    04/07
                  </p>
                </div>
              </td>
              <td className="pl-5">
                <div className="flex items-center">
                  <div>✉️</div>
                  <p className="text-sm leading-none text-gray-600 ml-2">23</p>
                </div>
              </td>
              <td className="pl-5">
                <button className="py-3 px-3 text-sm focus:outline-none leading-none text-red-700 bg-red-100 rounded">
                  Due today at 18:00
                </button>
              </td>
              <td className="pl-4">
                <button className="focus:ring-2 focus:ring-offset-2 focus:ring-red-300 text-sm leading-none text-gray-600 py-3 px-5 bg-gray-100 rounded hover:bg-gray-200 focus:outline-none">
                  View
                </button>
              </td>
              <td>
                <div className="relative px-5 pt-2">
                  <button className="focus:ring-2 rounded-md focus:outline-none"></button>
                  <div className="dropdown-content bg-white shadow w-24 absolute z-30 right-0 mr-6 hidden">
                    <div className="focus:outline-none focus:text-indigo-600 text-xs w-full hover:bg-indigo-700 py-4 px-4 cursor-pointer hover:text-white">
                      <p>Edit</p>
                    </div>
                    <div className="focus:outline-none focus:text-indigo-600 text-xs w-full hover:bg-indigo-700 py-4 px-4 cursor-pointer hover:text-white">
                      <p>Delete</p>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <tr className="h-3"></tr>
          </tbody>
        </table>
      </div>
    </>
  );
};

export default TodoPage;
