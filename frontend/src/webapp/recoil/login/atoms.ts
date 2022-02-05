import { atom } from "recoil";
import { ILoginType } from "src/webapp/types/loginTypes";

export const loginState = atom<ILoginType>({
  key: "login",
  default: { username: "", password: "" },
});