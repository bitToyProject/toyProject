import { selectorFamily } from "recoil";
import { apiPost } from "../service/login/Login.service";
import { signupState } from "./atom";
import { IResStatus, ISignupType, Param } from "./types";
<<<<<<< HEAD
import { checkNull, validateEmail, validateNumber} from "@/webapp/config/regExp/RegExp";
=======
import {
  checkNull,
  validateEmail,
  validateNumber,
} from "@/webapp/config/regExp/RegExp";
>>>>>>> 74665a02616ceec338867c6c7354bd2ee0a02942

export const signupSelector = selectorFamily<IResStatus, Param>({
  key: "auth/signup",
  get: (data: ISignupType) => async () => {
    console.log("data", data);
<<<<<<< HEAD

    /** 
     * 처음 렌더링 될 때 
     * post 되지 않게 null값 체크
    */
    if (checkNull(
      [data.firstName, 
      data.gender, 
      data.lastName, 
      data.nickName, 
      data.password, 
      data.phoneNum, 
      data.username])) return;
    
    if(validateEmail(data.username).isError) {
=======
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
>>>>>>> 74665a02616ceec338867c6c7354bd2ee0a02942
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
