import { atom } from "recoil";
import { INavbarType } from "src/types/navbarTypes";

export const navbarState = atom<INavbarType>({
  key: "navItem",
  default: {
    name: "Home",
    label: "홈"
  },
});
