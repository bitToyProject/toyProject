import { atom } from "recoil";
import { ISignupType } from "./types";

export const signupState = atom<ISignupType>({
    key: "signup",
    default: { email: "", password: ""}
  });