import React from "react";
import { css } from "@emotion/react";

const TodoTtile = () => {
  const todoTitle = css`
    display: flex;
    display: -webkit-flex;
    align-items: center;
    font-size: 1.8rem;
    color: var(--white);
    margin-bottom: 10px;
  `;
  return (
    <>
      <div css={todoTitle}>
        <div>TodoList With Recoil</div>
      </div>
    </>
  );
};

export default TodoTtile;
