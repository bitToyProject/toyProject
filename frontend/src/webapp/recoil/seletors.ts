import axios from "axios";
import { selectorFamily } from "recoil";

export const signupSelector = selectorFamily<any, any>({
    key: '/member/signup',
      get: (param:any)  => async() => {
  
        if(!param) return false
  
        console.log(param);
        const response = await axios({
          url: "http://172.16.101.44:8080/auth/signup",
          method: `POST`,
          data: param,
          headers: {
            "Content-Type": "application/json; charset=utf-8",
          },
        })
        console.log(response.data);
        return response.data;
    }
  })