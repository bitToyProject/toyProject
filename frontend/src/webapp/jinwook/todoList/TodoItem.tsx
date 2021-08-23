import React, { useCallback, useState } from "react";
import { SetterOrUpdater } from "recoil";
import { ITodoTypes } from "../recoil/todo";
import { FaPen } from "react-icons/fa";
import { MdClose } from "react-icons/md";
import { css } from "@emotion/react";

interface PropTypes {
  id: number;
  contents: string;
  isCompleted: boolean;

  onComplete: (id: number) => void;
  onDelete: (id: number) => void;

  todos: ITodoTypes[];
  setTodos: SetterOrUpdater<ITodoTypes[]>;
}
const TodoItem = ({
  id,
  contents,
  isCompleted,
  onComplete,
  onDelete,
  todos,
  setTodos,
}: PropTypes) => {
  const todoItem = css`
    display: flex;
    display: -webkit-flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    padding-bottom: 6px;
    border-bottom: 1px solid var(--white);
    font-size: 1.2rem;

    -moz-user-select: none;
    -webkit-user-drag: none;
    &-Completed {
      text-decoration-line: line-through;
      text-decoration-color: var(--red);
    }

    & > * {
      max-width: 100%;
      max-height: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      cursor: pointer;
    }

    &-Icons {
      display: flex;
      display: -webkit-flex;
      align-items: center;

      &-Pen {
        color: var(--green);
      }

      &-Close {
        color: var(--red);
        font-size: 1.9rem;
      }
    }
  `;
  const [isModal, setIsModal] = useState<boolean>(false);
  const [modifyContents, setModifyContents] = useState<string>("");
  const onModify = useCallback((): void => {
    setIsModal(true);
    setModifyContents(contents);
  }, [contents]);
  const onModifyTodo = useCallback((): void => {
    if (!modifyContents.trim()) {
      return;
    }
    setTodos(
      todos.map((todo: ITodoTypes) => {
        return todo.id === id ? { ...todo, contents: modifyContents } : todo;
      })
    );
    setIsModal(false);
  }, [id, modifyContents, setTodos, todos]);
  return (
    <>
      <div css={todoItem}>
        <div
          title={contents}
          css={isCompleted ? "TodoItem-Completed" : ""}
          onClick={() => onComplete(id)}
        >
          {contents}
        </div>

        <div css="TodoItem-Icons">
          <FaPen css="TodoItem-Icons-Pen" onClick={onModify} />
          <MdClose css="TodoItem-Icons-Close" onClick={() => onDelete(id)} />
        </div>
      </div>
    </>
  );
};

export default TodoItem;
