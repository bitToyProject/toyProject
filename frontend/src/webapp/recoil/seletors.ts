import { selectorFamily } from "recoil";
import { apiPost } from "../service/login/Login.service";
import { IResStatus, ISignupType, Param } from "./types";

export const signupSelector = selectorFamily<IResStatus, Param>({
    key: '/member/signup',
      get: (data:ISignupType)  => async() => {
  
        if(!data) return false;

        try {
          const response = await apiPost("/auth/signup", data);
          if (response.status === 200) {
            return response.data;
          }
        } catch (e:any) {
          return e.response.errorCode;   
        }    
  }
})
