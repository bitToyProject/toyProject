import { atom } from "recoil";
import { ISignupType } from "src/types/signupTypes";

export const emailState = atom<String>({
  key: "email",
  default: ""
})

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
