import { useInput } from "../../hook/useInput";

const EmailInput = () => {
  const name = useInput("", undefined);
  console.log("name: ", name);
  return (
    <>
      <label>이메일</label>
      <input type="text" {...name} />
    </>
  );
};

export default EmailInput;
