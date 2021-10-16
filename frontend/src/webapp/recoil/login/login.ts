import { setCookies } from "@/webapp/config/cookie/Cookie";
import { apiPost } from "@/webapp/service/login/Login.service";
import { atom, selectorFamily } from "recoil";

export interface ILoginType {
  email: string;
  password: string;
}
export interface IResStatus {
  status: number;
}
export const loginState = atom<ILoginType>({
  key: "login",
  default: { email: "", password: "" },
});

interface Param extends ILoginType {
  [key: string]: any;
}

export const apiLogin = selectorFamily<IResStatus, Param>({
  key: "auth/login",
  get: (data: ILoginType) => async () => {
    if (!data) return;
    try {
      const response = await apiPost("/auth/login", data);
      if (response.status === 200) {
        const accessToken = await response.data.data.accessToken;
        console.log("accessToken", accessToken);
        setCookies("loginToken", accessToken, {
          path: "/",
          // httpOnly: true,
          maxAge: 60 * 60 * 24 * 7,
        });
      }
      return response;
    } catch (e:any) {
      return e.response.status;
    }
  },
});
