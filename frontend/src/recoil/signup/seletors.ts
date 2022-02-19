import { selector, selectorFamily } from "recoil";
import { apiPost } from "src/service/login/Login.service";
import { IResStatus } from "src/types/loginTypes";
import { ISignupType } from "src/types/signupTypes";
import { checkNull, validateEmail, validateNumber } from "src/utils/regExp/RegExp";
import { emailState } from "./atoms";

export const signupSelector = selectorFamily<IResStatus, ISignupType>({
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