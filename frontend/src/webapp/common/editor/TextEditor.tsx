import React, { useState, useRef, useEffect } from 'react';
import { DraftEditorCommand, EditorState, Editor, RichUtils } from 'draft-js';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { Toolbar } from '@/webapp/common';

const TextEditor = () => {
    const [editorState, setEditorState] = useState<EditorState>(() =>
        EditorState.createEmpty()
    );
    const ref = useRef<Editor>(null);
    const inlineStyle = editorState.getCurrentInlineStyle();

    useEffect(() => {}, [editorState]);

    // 키조작 가능
    const handleKeyCommand = (command: DraftEditorCommand) => {
        const newState = RichUtils.handleKeyCommand(editorState, command);
        if (newState) {
            setEditorState(newState);
            return 'handled';
        }
        return 'not-handled';
    };

    const handleBlockStyle = (block: any): string => {
        switch (block.getType()) {
            case 'left':
                return 'align-left';
            case 'center':
                return 'align-center';
            case 'right':
                return 'align-right';
            default:
                return 'unstyled';
        }
    };

    return (
        <>
            <div
                css={css`
                    display: -webkit-box;
                    display: -ms-flexbox;
                    display: flex;
                    -webkit-box-pack: center;
                    -ms-flex-pack: center;
                    justify-content: center;
                    font-family: 'Sans-serif' !important;
                `}
            >
                <div
                    css={css`
                        width: calc(100vw - 360px);
                        margin: 10px 0px 0px 0px;
                        padding: 60px;
                        -webkit-box-shadow: 0px 1px 4px rgba(19, 24, 48, 0.2);
                        box-shadow: 0px 1px 4px rgba(19, 24, 48, 0.2);
                        border-radius: 8px;
                        font-size: 20px;
                    `}
                >
                    <Toolbar
                        RichUtils={RichUtils}
                        inlineStyle={inlineStyle}
                        editorState={editorState}
                        setEditorState={setEditorState}
                    />
                    <div
                    // css={css`
                    //     align-left: ${block.getType() === 'left'
                    //         ? `left`
                    //         : ``};
                    // `}
                    >
                        <Editor
                            editorKey={'editor'}
                            editorState={editorState}
                            onChange={setEditorState}
                            handleKeyCommand={handleKeyCommand}
                            blockStyleFn={handleBlockStyle}
                            ref={ref}
                        />
                    </div>
                </div>
            </div>
        </>
    );
};

export default TextEditor;
