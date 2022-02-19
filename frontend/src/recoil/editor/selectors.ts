import { selectorFamily } from "recoil";
import { IEditorType } from "src/types/editorTypes";

export const editorSelector = selectorFamily<any, any>({
  key: "editor",
  get: (data: IEditorType) => async () => {
    console.log("data", data);
    // try {
    //   const response = await apiPost("/auth/signup", data);
    //   console.log("data", data);
    //   if (response.status === 200) {
    //     return response.data;
    //   }
    // } catch (e: any) {
    //   return e.response.data;
    // }
  },
});

