import { atom } from "recoil";
import { ISignupType } from "./types";

export const signupState = atom<ISignupType>({
  key: "signup",
  default: {
    username: "",
    password: "",
    nickName: "",
    phoneNum: "",
    firstName: "",
    lastName: "",
    gender: 0,
  },
});
