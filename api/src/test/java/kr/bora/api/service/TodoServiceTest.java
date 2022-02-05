package kr.bora.api.service;

import kr.bora.api.todo.dto.PageRequestDto;
import kr.bora.api.todo.dto.PageResultDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testGetList() {
        PageRequestDto pageRequestDto = new PageRequestDto(1, 10, "t", "1");

        PageResultDto<TodoDto, Object[]> result = todoService.getList(pageRequestDto);

        System.out.println(result.getDtoList());

        for (TodoDto todoDto : result.getDtoList()) {
            System.out.println(todoDto);
        }

    }


}
