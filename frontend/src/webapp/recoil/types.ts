import { EditorState } from "draft-js";

export interface IEditorType {
  contents: string;
}

export interface EditorPropsType{
  editorState: EditorState,
  setEditorState: any
}

