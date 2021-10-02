import { atom } from "recoil";

export interface ISignupType {
  username: string;
  password: string;
  nickname: string;
}

export const signupState = atom<ISignupType>({
  key: "signup",
  default: { username: "", password: "", nickname: "" }
});

