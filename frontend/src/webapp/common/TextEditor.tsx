import React, { MouseEvent, useCallback, useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { Editor } from 'react-draft-wysiwyg';
import { EditorState, convertToRaw } from 'draft-js';
import { COLOR_GRAY } from '@/webapp/common/CCstyle/CCstyle';
import { ColoredButton } from '@/webapp/container';
import { IEditorType } from '@/webapp/recoil/types';
import { useRecoilState, useRecoilValue } from 'recoil';
import { editorSelector } from '@/webapp/recoil/seletors';
import { contentsState } from '@/webapp/recoil/atom';
import draftToHtml from 'draftjs-to-html';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';
import { useFileUpload } from '@/webapp/hook/useFileUpload';

const TextEditor = () => {
    const [contents, setContents] = useRecoilState<IEditorType>(contentsState);
    const [editorState, setEditorState] = useState<EditorState>(
        EditorState.createEmpty()
    );

    useRecoilValue(editorSelector(contents));

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
                    font-family sans-serif important!;
                `}
            >
                <Editor
                    wrapperClassName="wrapper-class"
                    editorClassName="editor-class"
                    toolbarClassName="toolbar-class"
                    localization={{
                        locale: 'ko',
                    }}
                    toolbar={{
                        inline: { inDropdown: true },
                        list: { inDropdown: true },
                        textAlign: { inDropdown: true },
                        link: { inDropdown: true },
                        history: { inDropdown: true },
                        image: {
                            uploadEnabled: true,
                            uploadCallback: useFileUpload,
                            previewImage: true,
                            inputAccept:
                                'image/gif,image/jpeg,image/jpg,image/png,image/svg',
                            alt: { present: false, mandatory: false },
                            defaultSize: {
                                height: 'auto',
                                width: 'auto',
                            },
                        },
                    }}
                    onEditorStateChange={handleChange}
                />
            </div>
            <ColoredButton
                disabled={false}
                btnLabel={'저장'}
                color={''}
                backgroundColor={''}
                isWhite
                handleClick={handleClick}
            />
        </>
    );
};

export default TextEditor;
