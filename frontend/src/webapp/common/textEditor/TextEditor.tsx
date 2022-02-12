import React, { MouseEvent, useCallback, useState } from "react";
/** srcjsxImportSource srcemotion/react */
import { css } from "@emotion/react";
import { Editor } from "react-draft-wysiwyg";
import { EditorState, convertToRaw } from "draft-js";
import { COLOR_GRAY } from "src/webapp/common/CCstyle/CCstyle";
import { ColoredButton } from "src/webapp/container";
import { useRecoilState, useRecoilValue } from "recoil";
import draftToHtml from "draftjs-to-html";
import { useFileUpload } from "src/webapp/hook/useFileUpload";
import Snackbar from "src/webapp/common/snackbar/Snackbar";
import { useSnackbar } from "src/webapp/hook/useSnackbar";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import { IEditorType } from "src/webapp/types/editorTypes";
import { contentsState } from "src/webapp/recoil/editor/atoms";
import { editorSelector } from "src/webapp/recoil/editor/selectors";

const TextEditor = () => {
  const [contents, setContents] = useRecoilState<IEditorType>(contentsState);
  const [editorState, setEditorState] = useState<EditorState>(
    EditorState.createEmpty()
  );

  useRecoilValue(editorSelector(contents));

  const { action, msg, show, openSnackbar } = useSnackbar();

  const handleChange = (state: EditorState) => {
    setEditorState(state);
  };

  const handleClick = useCallback(
    (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => {
      e.preventDefault();
      e.stopPropagation();

      const editorToHtml = draftToHtml(
        convertToRaw(editorState.getCurrentContent())
      );

      // 저장 할 내용
      console.log(editorToHtml);

      // status가 200인 경우 이런식으로 작성하기
      openSnackbar(true, "글이 작성되었습니다.");
    },
    [editorState]
  );

  return (
    <>
      <div
        css={css`
          height: 350px;
          border: 1px solid ${COLOR_GRAY};
          padding: 5px;
          border-radius: 2px;
          font-family: sans-serif important!;
        `}
      >
        <Editor
          wrapperClassName="wrapper-class"
          editorClassName="editor-class"
          toolbarClassName="toolbar-class"
          localization={{
            locale: "ko",
          }}
          toolbar={{
            inline: { inDropdown: true },
            list: { inDropdown: true },
            textAlign: { inDropdown: true },
            link: { inDropdown: true },
            history: { inDropdown: true },
            // image: uploadCallback: This is image upload callBack. It should return a promise that resolves to give image src. Default value is true.
            // Both above options of uploadEnabled and uploadCallback should be present for upload to be enabled.
            // Promise should resolve to return an object { data: { link: <THE_URL>}}.
            image: {
              uploadEnabled: true,
              uploadCallback: useFileUpload,
              previewImage: true,
              inputAccept: "image/gif,image/jpeg,image/jpg,image/png,image/svg",
              alt: { present: false, mandatory: false },
              defaultSize: {
                height: "auto",
                width: "auto",
              },
            },
          }}
          onEditorStateChange={handleChange}
        />
      </div>
      <ColoredButton
        disabled={false}
        btnLabel={"저장"}
        color={""}
        backgroundColor={""}
        isWhite
        handleClick={handleClick}
      />
      <Snackbar action={action} msg={msg} show={show} />
    </>
  );
};

export default TextEditor;
