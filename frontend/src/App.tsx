import { Route } from 'react-router';
import { TodoListApp } from '@/webapp/todoList/index';
import { useRecoilValue } from 'recoil';
import { errorMsgState } from '@/webapp/recoil/common/index';
import { LoginApp, SignupApp } from '@/webapp/container';

const App = () => {
    const errorMsg = useRecoilValue(errorMsgState);

    return (
        <>
            <Route exact path="/" component={LoginApp}></Route>
            {/* 회원가입 */}
            <Route exact path={'/member/signup'} component={SignupApp}></Route>
            {/* recoil test todoList */}
            <Route exact path={'/todo'} component={TodoListApp}></Route>
        </>
    );
};

export default App;
