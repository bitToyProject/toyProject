import { ITodoType } from '../recoil/todo';
import { SetterOrUpdater } from 'recoil';
import { ChangeEvent, useCallback, useState } from 'react';

interface PropTypes {
    todo: {
        id: number;
        contents: string;
        isCompleted: boolean;

        handleComplete: (id: number) => void;
        handleDelete: (id: number) => void;

        todos: ITodoType[];
        setTodos: SetterOrUpdater<ITodoType[]>;
    };
}

const TodoItem = (todo: PropTypes['todo']): JSX.Element => {
    const [visible, setVisible] = useState<boolean>(false);
    const [modifyContents, setModifyContents] = useState<string>('');

    const handleChange = useCallback(
        (e: ChangeEvent<HTMLInputElement>): void => {
            e.preventDefault();

            const { value } = e.target;
            setModifyContents(value);
        },
        [setModifyContents]
    );

    const handleModify = useCallback((): void => {
        setVisible(true);
        console.log(visible);
        setModifyContents(todo.contents);
    }, [todo.contents]);

    const modifyTodo = useCallback((): void => {
        todo.setTodos(
            todo.todos.map((t: ITodoType) => {
                return t.id === todo.id
                    ? { ...t, contents: modifyContents }
                    : t;
            })
        );
        setVisible(false);
    }, [modifyContents, todo]);

    return (
        <>
            <div>
                <div onClick={() => todo.handleComplete(todo.id)}>
                    {todo.contents}
                </div>
                {visible ? (
                    <div className="modify">
                        <input
                            type="text"
                            value={modifyContents}
                            onChange={handleChange}
                        />
                        <button onClick={modifyTodo}>수정완료</button>
                    </div>
                ) : (
                    <p></p>
                )}
                <div>
                    <button onClick={() => todo.handleDelete(todo.id)}>
                        삭제하기
                    </button>
                    <button onClick={handleModify}>수정하기</button>
                </div>
            </div>
        </>
    );
};

export default TodoItem;
