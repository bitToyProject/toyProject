import { Route } from 'react-router';
import { LoginApp } from '@/webapp/member/index';
import { TodoListApp } from './webapp/todoList/index';

const App = () => {
    return (
        <>
            <Route exact path={'/user/login'} component={LoginApp}></Route>
            <Route exact path={'/todo'} component={TodoListApp}></Route>
        </>
    );
};

export default App;
