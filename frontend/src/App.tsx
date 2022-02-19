import { Suspense } from "react";
import "src/tailwind.output.css";
import { Route } from "react-router";
import { Routes } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "react-query";
import Header from "./layouts/header/Header";
import Loading from "./components/common/loading/Loading";
import LoginContainer from "./components/user/login/LoginContainer";
import SignupContainer from "./components/user/signup/SignupContainer";
import MailContainer from "./components/user/mail/MailContainer";
import TodoContainer from "./components/todo/TodoContainer";

const App = () => {
  const queryClient = new QueryClient();

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <Suspense fallback={<Loading />}>
          <Header />
          <Routes>
            <Route path="" element={<LoginContainer />}></Route>
            <Route path={"user/signup"} element={<SignupContainer />}></Route>
            <Route path={"user/mail_auth"} element={<MailContainer />}></Route>
            <Route
              path={"user/find-password"}
              element={<MailContainer />}
            ></Route>
            <Route path={"user/myPage"} element={<MailContainer />}></Route>
            <Route path={"task/todo"} element={<TodoContainer />}></Route>
            {/* <Route path={"task/editorTest"} element={<TextEditor />}></Route> */}
          </Routes>
        </Suspense>
      </QueryClientProvider>
    </>
  );
};

export default App;
