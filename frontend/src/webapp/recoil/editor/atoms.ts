import { atom } from "recoil";
import { IEditorType } from "src/webapp/types/editorTypes";

export const contentsState = atom<IEditorType>({
  key: "contents",
  default: {
    contents: ""
  },
});
