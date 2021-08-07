import { Route } from 'react-router';
import { LoginApp } from 'webapp/member/index';

const App = () => {
    return (
        <>
            <Route exact path={'/user/login'} component={LoginApp} />
        </>
    );
};

export default App;
