import { selectorFamily } from "recoil";
import { apiPost } from "src/service/login/Login.service";
import { IResStatus, ILoginType } from "src/types/loginTypes";
import { setCookies } from "src/utils/cookie/Cookie";

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
    } catch (e: any) {
      return e.response.status;
    }
  },
});
