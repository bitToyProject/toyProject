import { atom } from "recoil";
import { ILoginType } from "src/types/loginTypes";

export const loginState = atom<ILoginType>({
  key: "login",
  default: { username: "", password: "" },
});
