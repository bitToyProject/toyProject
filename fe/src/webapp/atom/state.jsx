import { atom, selector } from "recoil";
import { LoginService } from "webapp/member";

export const loginState = atom({
    key : "loginState",
    default: {}
})

export const getLoginSelector = selector({
    key: "user/login",
    get: async ({ get }) => {
        const res = await LoginService.apiLogin(get(loginState));

        return res.data;
    }
});