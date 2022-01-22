import { atom, selector, selectorFamily } from "recoil";
import { checkNull, validateEmail, validateNumber } from "src/webapp/config/regExp/RegExp";
import { apiPost } from "src/webapp/service/login/Login.service";

export interface ISignupType {
  username: string;
  password: string;
  nickName: string;
  phoneNum: string;
  firstName: string;
  lastName: string;
  gender: number;
}

export interface IResStatus {
  result: string;
}

export interface Param extends ISignupType {
  [key: string]: any;
}

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

export const signupSelector = selectorFamily<IResStatus, Param>({
  key: "auth/signup",
  get: (data: ISignupType) => async () => {
    console.log("data", data);
    if (
      checkNull([
        data.firstName,
        data.gender,
        data.lastName,
        data.nickName,
        data.password,
        data.phoneNum,
        data.username,
      ])
    )
      return;

    if (validateEmail(data.username).isError) {
      alert(validateEmail(data.username).msg);
      return false;
    }

    if (validateNumber(data.phoneNum).isNumberError) {
      alert(validateNumber(data.phoneNum).numberMsg);
      return false;
    }

    try {
      const response = await apiPost("/auth/signup", data);
      console.log("data", data);
      if (response.status === 200) {
        return response.data;
      }
    } catch (e: any) {
      return e.response.data;
    }
  },
});

export const emailCheckSelector = selector<String>({
  key : "auth/check",
  get: async ({ get }) => {
    const username = get(emailState);
    console.log(username);
    if(!username) return;
    try {
      const response = await apiPost(`/auth/check/${username}`, username);
      console.log("username", username);
      if (response.status === 200) {
        return response.data;
      }
    } catch (e: any) {
      // return e.response.data;
      return;
    }
  }
})