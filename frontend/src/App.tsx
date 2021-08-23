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
};

export default App;
