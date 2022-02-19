package kr.bora.api.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class TodoRepositoryTest {

//    @Autowired
//    private TodoRepository todoRepository;
//
//    @Test
//    public void insertTodo() {
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            User user = User.builder()
//                    .userId(SecurityUtil.getCurrentUserId())
//                    .build();
//
//            Todo todo = Todo.builder()
//                    .title("title" + i)
//                    .description("description" + i)
//                    .start("start" +i)
//                    .end("end" +i)
//                    .viewer("viewer"+ i)
//                    .priority(1+i)
//                    .user(user)
//                    .build();
//
//            todoRepository.save(todo);
//        });
//    }
//
//    @Test
//    public void testRead() {
//        Optional<Todo> result = todoRepository.findById(10L);
//
//        Todo todo = result.get();
//
//        System.out.println(todo);
//        System.out.println(todo.getTodoId());
//    }
//
//    //queryDsl 테스트
//    @Test
//    public void testQuery1() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("todoId").descending());
//
//        Page<Object[]> result = todoRepository.searchPage("t", "5", pageable);
//    }
}
