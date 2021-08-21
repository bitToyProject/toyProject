import { type } from "os";
import { useState } from "react";
import { EmailInput, PasswordInput } from "..";

type info = { email: string; password: string };
const LoginApp = () => {
  const [userInfo, setUserInfo] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    e.stopPropagation();
    const { name, value } = e.target;
  };

  return (
    <>
      <EmailInput />
      <PasswordInput />
    </>
  );
};
export default LoginApp;
