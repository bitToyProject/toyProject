import { atom } from "recoil";
import { IEditorType } from "./types";

export const contentsState = atom<IEditorType>({
  key: "contents",
  default: {
    contents: ""
  },
});
