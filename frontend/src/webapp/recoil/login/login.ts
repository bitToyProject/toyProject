import { atom, selector } from "recoil";

export interface ILoginType {
  username: string;
  password: string;
}

export const loginState = atom<ILoginType>({
  key: "login",
  default: { username: "", password: "" },
});

// export const apiLogin = selector({
//   key:"/login",
//   get: async ({get}) =>{
//     const response = await
//   }
// })
