import { atom } from "recoil";
import { IEditorType, ISignupType } from "./types";

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

export const contentsState = atom<IEditorType>({
  key: "contents",
  default: {
    contents: ""
  },
});
