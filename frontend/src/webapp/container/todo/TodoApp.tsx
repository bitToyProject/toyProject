import { Header, InputModule, Layout } from 'src/webapp/common';
import { TodoPage } from 'src/webapp/components';

const TodoApp = () => {
  return (
    <>
      <Header />
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
        <TodoPage />
      </Layout>
    </>
  );
};

export default TodoApp;
