import { atom } from "recoil";

export const errorMsgState = atom<string | null>({
  key: "errorMsgState",
  default: null,
});
