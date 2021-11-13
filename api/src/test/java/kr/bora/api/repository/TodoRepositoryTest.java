package kr.bora.api.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTodo() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            User user = User.builder()
                    .userId(1L + i)
                    .build();

            Todo todo = Todo.builder()
                    .title("title" + i)
                    .description("description" + i)
                    .start("start" +i)
                    .end("end" +i)
                    .viewer("viewer"+ i)
                    .priority(1+i)
                    .user(user)
                    .build();

            todoRepository.save(todo);
        });
    }

    @Test
    public void testRead() {
        Optional<Todo> result = todoRepository.findById(10L);

        Todo todo = result.get();

        System.out.println(todo);
        System.out.println(todo.getTodoId());
    }
}
