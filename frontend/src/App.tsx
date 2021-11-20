import { Route } from 'react-router';
import { LoginApp, SignupApp, MailApp } from '@/webapp/container';
import { Suspense } from 'react';
import Loading from './webapp/common/Loading';
import TextEditor from '@/webapp/common/TextEditor';

const App = () => {
    return (
        <>
            <Suspense fallback={<Loading />}>
                <Route exact path="/" component={LoginApp}></Route>
                {/* 회원가입 */}
                <Route
                    exact
                    path={'/member/signup'}
                    component={SignupApp}
                ></Route>
                <Route
                    exact
                    path={'/member/mail_auth'}
                    component={MailApp}
                ></Route>
                <Route
                    exact
                    path={'/editorTest'}
                    component={TextEditor}
                ></Route>
                {/* recoil test todoList */}
            </Suspense>
        </>
    );
};

export default App;
