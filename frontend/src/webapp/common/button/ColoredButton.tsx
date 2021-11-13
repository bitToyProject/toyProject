/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import React, { MouseEvent } from 'react';
import { COLOR_BLACK } from '../CCstyle/CCstyle';

interface PropTypes {
    btn: {
        disabled: boolean;
        btnLabel: string;
        color: string;
        backgroundColor: string;
        isWhite: boolean;
        handleClick: (
            e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>
        ) => void;
    };
}
const ColoredButton = (btn: PropTypes['btn']) => {
    return (
        <>
            <button
                disabled={btn.disabled}
                onClick={(e) => btn.handleClick(e)}
                css={css`
                    width: 100%;
                    height: 100%;
                    color: ${btn.isWhite ? `${COLOR_BLACK}` : btn.color};
                    border: ${btn.isWhite
                        ? `0.15rem solid ${COLOR_BLACK} `
                        : 0};
                    border-radius: 10px;
                    background-color: ${btn.isWhite
                        ? ' #fffff'
                        : btn.backgroundColor};
                `}
            >
                {btn.btnLabel}
            </button>
        </>
    );
};

export default ColoredButton;
