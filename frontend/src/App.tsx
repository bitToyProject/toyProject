import { Route } from 'react-router';
import { LoginApp, SignupApp, MailApp, TodoApp } from 'src/webapp/container';
import { Suspense } from 'react';
import Loading from 'src/webapp/common/Loading';
import TextEditor from 'src/webapp/common/TextEditor';
import 'src/tailwind.output.css';

const App = () => {
  return (
    <>
      <Suspense fallback={<Loading />}>
        <Route exact path="/" component={LoginApp}></Route>
        {/* 회원가입 */}
        <Route exact path={'/member/signup'} component={SignupApp}></Route>
        <Route exact path={'/member/mail_auth'} component={MailApp}></Route>
        {/* 마이페이지 */}
        <Route exact path={'/member/myPage'} component={MailApp}></Route>
        {/* 메인페이지 */}
        <Route exact path={'/task/todo'} component={TodoApp}></Route>
        <Route exact path={'/task/editorTest'} component={TextEditor}></Route>
      </Suspense>
    </>
  );
};

export default App;
