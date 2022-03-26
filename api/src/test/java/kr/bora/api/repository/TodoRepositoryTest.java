package kr.bora.api.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@WithMockUser(roles = "USER")
@Slf4j
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTodo() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
//            User user = User.builder()
//                    .userId(SecurityUtil.getCurrentUserId())
//                    .build();

            Todo todo = Todo.builder()
                    .title("알고리즘" + i)
                    .description("알고리즘" + i + "문제 풀거야")
                    .start("2022-03" + "-" + i)
                    .end("2022-03" + "-" + i)
                    .assignee("같이 푸는 사람" + i)
                    .priority(1 + i)
                    .done(false)
                    .build();

            todoRepository.save(todo);
        });
    }

    @Test
    public void testRead() {
        Optional<Todo> result = todoRepository.findById(2L);

        Todo todo = result.get();

        Assertions.assertThat(2L).isEqualTo(2L);
    }

    //queryDsl 테스트
    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("todoId").descending());

        Page<Object[]> result = todoRepository.searchPage("t", "5", pageable);
    }
}
