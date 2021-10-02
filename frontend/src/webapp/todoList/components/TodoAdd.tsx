import { ChangeEvent, useCallback } from "react";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { inputState, ITodoType, todoState } from "../recoil/todo";

const TodoAdd = () => {
  const [contents, setContents] = useRecoilState<string>(inputState);

  const todos = useRecoilValue<ITodoType[]>(todoState); // get 변수
  const setTodos = useSetRecoilState<ITodoType[]>(todoState); // setter 지정

  const handleChagne = useCallback(
    (e: ChangeEvent<HTMLInputElement>): void => {
      e.preventDefault();
      const { value } = e.target;
      setContents(value);
    },
    [setContents]
  );

  const handleClick = useCallback(
    (e: any): void => {
      e.preventDefault();
      const nextId: number =
        todos.length > 0 ? todos[todos.length - 1].id + 1 : 0;
      const todo: ITodoType = {
        id: nextId,
        contents,
        isCompleted: false,
      };

      setTodos([...todos, todo]);
      setContents("");
    },
    [contents, setContents, todos, setTodos]
  );

  return (
    <>
      <input
        type="text"
        placeholder="todo를 입력해주세요"
        value={contents}
        onChange={handleChagne}
      />
      <button onClick={handleClick}>추가</button>
    </>
  );
};

export default TodoAdd;
