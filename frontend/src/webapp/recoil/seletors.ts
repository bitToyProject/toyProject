import { selectorFamily } from "recoil";
import { apiPost } from "../service/login/Login.service";
import { signupState } from "./atom";
import { IResStatus, ISignupType, Param } from "./types";

export const signupSelector = selectorFamily<IResStatus, Param>({
  key: "auth/signup",
  get: (data: ISignupType) => async () => {
    console.log("data", data);
    if (!data) return;
    try {
      const response = await apiPost("/auth/signup", data);
      console.log("data", data);
      if (response.status === 200) {
        return response.data;
      }
    } catch (e: any) {
      return e.response.status;
    }
  },
});
