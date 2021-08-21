import { Route } from "react-router";
import { TodoListApp } from "./webapp/todoList";

const App = () => {
  return (
    <>
      <Route path="/todo" component={TodoListApp}/>
    </>
  );
};

export default App;
