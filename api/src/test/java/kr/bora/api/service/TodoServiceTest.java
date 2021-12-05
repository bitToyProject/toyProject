package kr.bora.api.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class TodoServiceTest {

//    @Autowired
//    private TodoService todoService;
//
//    @Test
//    public void testGetList() {
//        List<Todo> list = todoService.getList();
//
//        System.out.println(list);
//
//    }
//
//    @Test
//    public void testGet() {
//        Long todoId = 10L;
//
//        TodoDto todoDto = todoService.get(todoId);
//
//        System.out.println(todoDto);
//    }
//
//    @Test
//    public void testRemove() {
//        Long todoId = 10L;
//
//        todoService.todoRemove(todoId);
//    }

//    @Test
//    public void testRegister() {
//        TodoDto dto = TodoDto.builder()
//                .title("testad;fk;laks")
//                .description("testasfasfasf")
//                .start("asfasfas")
//                .end("dasfasfasf")
//                .priority(1)
//                .viewer("tasfasf")
//                .userId(1L)
//                .build();
//        todoService.TodoSave(dto);
//    }
//
//    @Transactional
//    @Test
//    @Commit
//    public void testModify() {
//        TodoDto dto = TodoDto.builder()
//                .todoId(36L)
//                .title("변경")
//                .description("변경2")
//                .viewer("변3경")
//                .priority(2)
//                .start("23")
//                .end("2")
//                .userId(1L)
//                .build();
//
//        todoService.modify(dto);
//    }
}
