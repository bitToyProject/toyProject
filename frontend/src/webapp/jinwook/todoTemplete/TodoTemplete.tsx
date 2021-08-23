import { css } from "@emotion/react";
import React from "react";
import TodoInput from "../todoInput/TodoInput";
import TodoList from "../todoList/TodoList";
import TodoTtile from "../todoTitle/TodoTtile";

const TodoTemplete = () => {
  const todoTemplte = css`
    width: 100%;
    min-height: 100%;
    background-color: var(--main);
    display: flex;
    display: -webkit-flex;
    justify-content: center;
  `;
  const contents = css`
    margin: auto;
    display: flex;
    display: -webkit-flex;
    flex-direction: column;
    -ms-flex-direction: column;
    align-items: center;
  `;
  return (
    <>
      <div css={todoTemplte}>
        <div css={contents}>
          <TodoTtile />
          <TodoList />
          <TodoInput />
        </div>
      </div>
    </>
  );
};

export default TodoTemplete;
