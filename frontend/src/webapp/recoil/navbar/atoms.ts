import { atom } from "recoil";
import { INavbarType } from "src/webapp/types/navbarTypes";

export const navbarState = atom<INavbarType>({
  key: "navItem",
  default: {
    name: "Home",
    label: "홈"
  },
});
