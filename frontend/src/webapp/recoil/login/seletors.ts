import { setCookies } from "src/webapp/config/cookie/Cookie";
import { apiPost } from "src/webapp/service/login/Login.service";
import { selectorFamily } from "recoil";
import { ILoginType, IResStatus } from "src/webapp/types/loginTypes";

export const apiLogin = selectorFamily<IResStatus, ILoginType>({
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
