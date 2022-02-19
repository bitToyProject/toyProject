import React from 'react';
import InputModule from '../common/input/InputModule';
import Layout from './Layout';

const TodoContainer = () => {
  return (
    <>
      <Layout title="Tasks">
        <InputModule
          type={'nickName'}
          disabled={false}
          placeholder={'✍️ Add a new task'}
          onChange={(value: string) => console.log('test')}
        />
        {/* 다음에 할 일 트윌윈드 재사용 가능하게 변경하기 */}
        <button className="focus:ring-2 focus:ring-offset-2 focus:ring-indigo-600 mt-4 sm:mt-0 inline-flex items-start justify-start px-6 py-3 bg-indigo-700 hover:bg-indigo-600 focus:outline-none rounded">
          <p className="text-sm font-medium leading-none text-white">추가</p>
        </button>
        <div className="sm:flex items-center justify-between">
          <div className="flex items-center">
            <div className="rounded-full focus:outline-none focus:ring-2  focus:bg-indigo-50 focus:ring-indigo-800">
              <div className="py-2 px-8 bg-indigo-100 text-indigo-700 rounded-full">
                <p>전체</p>
              </div>
            </div>
            <div className="rounded-full focus:outline-none focus:ring-2 focus:bg-indigo-50 focus:ring-indigo-800 ml-4 sm:ml-8">
              <div className="py-2 px-8 text-gray-600 hover:text-indigo-700 hover:bg-indigo-100 rounded-full ">
                <p>완료</p>
              </div>
            </div>
            <div className="rounded-full focus:outline-none focus:ring-2 focus:bg-indigo-50 focus:ring-indigo-800 ml-4 sm:ml-8">
              <div className="py-2 px-8 text-gray-600 hover:text-indigo-700 hover:bg-indigo-100 rounded-full ">
                <p>진행중</p>
              </div>
            </div>
          </div>
        </div>
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
      </Layout>
      {/* 다음에 할 일 트윌윈드 재사용 가능하게 변경하기 */}
    </>
  );
};

export default TodoContainer;
