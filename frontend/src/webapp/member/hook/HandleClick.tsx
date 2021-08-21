import { useInput } from "./useInput";
import { EmailInput } from "@/webapp/member/index";
const HandleClick = (value: any) => {
  const handleClick = () => {
    console.log("clicked!!!!!");
    console.log("Clicked value: ", value);
  };
  return (
    <>
      <button onClick={() => handleClick()}>클릭이요</button>
    </>
  );
};

export default HandleClick;
