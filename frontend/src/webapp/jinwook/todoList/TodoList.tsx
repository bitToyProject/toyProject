import { css } from "@emotion/react";
import React, { useCallback } from "react";
import { useRecoilState } from "recoil";
import { ITodoTypes, todosState } from "../recoil/todo";
import TodoItem from "./TodoItem";

const TodoList = () => {
  const todoList = css`
    width: 500px;
    height: 500px;
    max-height: 500px;
    position: relative;
    border: 2px solid var(--white);
    border-radius: 10px;
    margin-bottom: 10px;
    padding: 1.5rem;
    overflow-x: hidden;
    overflow-y: auto;
  `;
  const noList = css`
    width: 100%;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  `;
  const [todos, setTodos] = useRecoilState<ITodoTypes[]>(todosState);
  const onComplete = useCallback(
    (id: number): void => {
      setTodos(
        todos.map((todo: ITodoTypes) => {
          return todo.id === id
            ? { ...todo, isCompleted: !todo.isCompleted }
            : todo;
        })
      );
    },
    [setTodos, todos]
  );
  const onDelete = useCallback(
    (id: number) => {
      setTodos(todos.filter((todo: ITodoTypes) => todo.id !== id));
    },
    [setTodos, todos]
  );
  return (
    <>
      <div className="TodoList">
        {todos.length > 0 ? (
          todos.map((todo: ITodoTypes) => {
            const { id, contents, isCompleted } = todo;

            return (
              <TodoItem
                key={id}
                id={id}
                contents={contents}
                isCompleted={isCompleted}
                onComplete={onComplete}
                onDelete={onDelete}
                todos={todos}
                setTodos={setTodos}
              />
            );
          })
        ) : (
          <div className="TodoList-NoList">
            Todo가 없습니다. 자유롭게 추가해보세요!
          </div>
        )}
      </div>
    </>
  );
};

export default TodoList;
