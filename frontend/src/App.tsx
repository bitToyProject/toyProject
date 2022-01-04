import { LoginApp, SignupApp, MailApp, TodoApp } from 'src/webapp/container';
import { Suspense } from 'react';
import Loading from './webapp/common/Loading';
import TextEditor from 'src/webapp/common/TextEditor';
import 'src/tailwind.output.css';
import { Route } from 'react-router';
import { Routes } from 'react-router-dom';

const App = () => {
  return (
    <>
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/" element={<LoginApp />}></Route>
          {/* 회원가입 */}
          <Route path={'/member/signup'} element={<SignupApp />}></Route>
          <Route path={'/member/mail_auth'} element={<MailApp />}></Route>
          {/* 마이페이지 */}
          <Route path={'/member/myPage'} element={<MailApp />}></Route>
          {/* 메인페이지 */}
          <Route path={'/task/todo'} element={<TodoApp />}></Route>
          <Route path={'/task/editorTest'} element={<TextEditor />}></Route>
        </Routes>
      </Suspense>
    </>
  );
};

export default App;
