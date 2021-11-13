import React, { useEffect, useState } from 'react';
import {
    blockButtonOptions,
    toggleButtonOptions,
} from '@/webapp/common/editor/EditorButton';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { COLOR_WHITE, COLOR_GRAY } from '@/webapp/common/CCstyle/CCstyle';
import { ToggleButton } from '@/webapp/recoil/types';
import { EditorState } from 'draft-js';

// 타입 변경하기
interface PropTypes {
    RichUtils: any;
    editorState: EditorState;
    setEditorState: any;
    inlineStyle: any;
}

const Toolbar = (props: PropTypes) => {
    const [blockButton, setBlockButton] = useState('');
    const [toggleButton, setToggleButton] = useState<ToggleButton>({
        BOLD: false,
        ITALIC: false,
        UNDERLINE: false,
        STRIKETHROUGH: false,
    });

    useEffect(() => {
        const BOLD = props.inlineStyle.has('BOLD');
        const ITALIC = props.inlineStyle.has('ITALIC');
        const UNDERLINE = props.inlineStyle.has('UNDERLINE');
        const STRIKETHROUGH = props.inlineStyle.has('STRIKETHROUGH');
        const currentSelection = props.editorState.getSelection();
        const currentKey = currentSelection.getStartKey();
        const currentBlock = props.editorState
            .getCurrentContent()
            .getBlockForKey(currentKey);

        setToggleButton({ BOLD, ITALIC, UNDERLINE, STRIKETHROUGH });
        setBlockButton(currentBlock.getType());
    }, []);

    // block 변경
    const handleBlockClick = (e: React.MouseEvent, blockType: string) => {
        console.log('blockType', blockType);
        e.preventDefault();
        if (blockType === null) return;
        props.setEditorState(
            props.RichUtils.toggleBlockType(props.editorState, blockType)
        );
    };

    // inlineStyles 변경
    const handleToggleClick = (e: React.MouseEvent, inlineStyle: string) => {
        console.log('inlineStyle', inlineStyle);
        e.preventDefault();
        if (inlineStyle === null) return;
        props.setEditorState(
            props.RichUtils.toggleInlineStyle(props.editorState, inlineStyle)
        );
    };

    return (
        <>
            <div
                css={css`
                    display: -webkit-box;
                    display: -ms-flexbox;
                    display: flex;
                    -webkit-box-align: center;
                    -ms-flex-align: center;
                    align-items: center;
                    margin-bottom: 10px;
                `}
            >
                {blockButtonOptions.map((buttonOption, idx) => (
                    <button
                        onMouseDown={(e) =>
                            handleBlockClick(e, buttonOption.action)
                        }
                        key={idx}
                        css={css`
                            border: none;
                            border-radius: 4px;
                            padding: 4px;
                            margin: 0px 4px;
                            backgroundcolor: ${buttonOption.action ===
                            blockButton
                                ? `${COLOR_GRAY}`
                                : `${COLOR_WHITE}`};
                        `}
                    >
                        <img
                            css={css`
                                width: 2rem;
                                heigh: 2rem;
                            `}
                            src={`/assets/editorIcons/${buttonOption.name}`}
                            alt=""
                        />
                    </button>
                ))}
                {toggleButtonOptions.map((buttonOption, idx) => (
                    <button
                        onMouseDown={(e) =>
                            handleToggleClick(e, buttonOption.action)
                        }
                        key={idx}
                        css={css`
                            border: none;
                            border-radius: 4px;
                            padding: 4px;
                            margin: 0px 4px;
                        `}
                    >
                        <img
                            css={css`
                                width: 2rem;
                                heigh: 2rem;
                            `}
                            src={`/assets/editorIcons/${buttonOption.name}`}
                            alt=""
                        />
                    </button>
                ))}
            </div>
        </>
    );
};

export default Toolbar;
