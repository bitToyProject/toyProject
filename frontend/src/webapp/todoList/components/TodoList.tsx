import { useCallback } from 'react';
import { useRecoilState } from 'recoil';
import { TodoItem } from '..';
import { ITodoType, todoState } from '../recoil/todo';

const TodoList = () => {
    const [todos, setTodos] = useRecoilState<ITodoType[]>(todoState);

    const handleComplete = useCallback(
        (id: number): void => {
            setTodos(
                todos.map((todo: ITodoType) => {
                    return todo.id === id
                        ? { ...todo, isCompleted: !todo.isCompleted }
                        : todo;
                })
            );
        },
        [setTodos, todos]
    );

    const handleDelete = useCallback(
        (id: number): void => {
            setTodos(todos.filter((todo: ITodoType) => todo.id !== id));
        },
        [setTodos, todos]
    );

    return (
        <>
            <h1>투두리스트</h1>
            {todos.length > 0 ? (
                todos.map((todo: ITodoType) => {
                    const { id, contents, isCompleted } = todo;

                    return (
                        <TodoItem
                            key={id}
                            id={id}
                            contents={contents}
                            isCompleted={isCompleted}
                            handleComplete={handleComplete}
                            handleDelete={handleDelete}
                            todos={todos}
                            setTodos={setTodos}
                        />
                    );
                })
            ) : (
                <p>todo가 없습니다.</p>
            )}
        </>
    );
};

export default TodoList;
