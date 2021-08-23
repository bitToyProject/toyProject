<<<<<<< HEAD
import { Route } from "react-router";
import { LoginApp } from "@/webapp/member/index";
import TodoTemplete from "./webapp/jinwook/todoTemplete/TodoTemplete";

const App = () => {
  return (
    <>
      <Route exact path={"/user/login"} component={LoginApp}></Route>
      <Route exact path={"/jinwook/todo"} component={TodoTemplete}></Route>
    </>
  );
=======
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
>>>>>>> 79b3f290d10d73d2d0e2593ea5b52597ae72d3ae
};

export default App;
