import { atom } from "recoil";
import { ISignupType } from "./type";

export const signupState = atom<ISignupType>({
    key: "signup",
    default: { email: "", password: ""}
  });