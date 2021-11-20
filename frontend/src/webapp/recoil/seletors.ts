import { selectorFamily } from "recoil";
import { apiPost } from "../service/login/Login.service";
import { IEditorType, IResStatus, ISignupType, Param } from "./types";
import {
  checkNull,
  validateEmail,
  validateNumber,
} from "@/webapp/config/regExp/RegExp";

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

export const editorSelector = selectorFamily<any, any>({
  key: "editor",
  get: (data: IEditorType) => async () => {
    console.log("data", data);
    // try {
    //   const response = await apiPost("/auth/signup", data);
    //   console.log("data", data);
    //   if (response.status === 200) {
    //     return response.data;
    //   }
    // } catch (e: any) {
    //   return e.response.data;
    // }
  },
});

