import { useInput } from "../../../hook/useInput";

const PasswordInput = () => {
  const maxLength = (value: string) => value.length < 10;

  const inputPassword = useInput("", maxLength);

  return (
    <>
      <label>패스워드</label>
      <input type={"password"} {...inputPassword} />
    </>
  );
};

export default PasswordInput;
