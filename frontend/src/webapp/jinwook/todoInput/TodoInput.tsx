import { css } from "@emotion/react";
import React, { useCallback } from "react";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { inputState, ITodoTypes, todosState } from "../recoil/todo";

const TodoInput = () => {
  const todoInput = css`
    width: 100%;
    display: flex;
    display: -webkit-flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid var(--white);
    padding: 0 4px;
    input {
      flex: 1;
      outline: none;
      border: none;
      color: var(--white);
      background-color: var(--main);
      padding: 5px 2px;
      font-size: 1.2rem;
      ::placeholder {
        color: var(--white);
      }
    }
    button {
      color: var(--white);
      font-size: 1.4rem;
      cursor: pointer;
    }
  `;
  const [contents, setContents] = useRecoilState<string>(inputState);

  const todos = useRecoilValue<ITodoTypes[]>(todosState);
  const setTodos = useSetRecoilState<ITodoTypes[]>(todosState);
  const addTodo = useCallback((): void => {
    if (!contents.trim()) {
      return;
    }
    const nextId: number =
      todos.length > 0 ? todos[todos.length - 1].id + 1 : 0;
    const todo: ITodoTypes = {
      id: nextId,
      contents,
      isCompleted: false,
    };
    setTodos([...todos, todo]);
    setContents("");
  }, [contents, setContents, setTodos, todos]);

  const onChange = useCallback(
    (e: any): void => {
      const { value } = e.target;
      setContents(value);
    },
    [setContents]
  );

  return (
    <>
      <div css={todoInput}>
        <input
          type="text"
          className="TodoInput-Input"
          placeholder="Todo를 입력해보세요!"
        />
        <button />
      </div>
    </>
  );
};

export default TodoInput;
