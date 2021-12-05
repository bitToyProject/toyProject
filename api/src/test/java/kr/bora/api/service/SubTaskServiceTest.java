//package kr.bora.api.service;
//
//import kr.bora.api.subtask.domain.SubTask;
//import kr.bora.api.subtask.dto.SubTaskDto;
//import kr.bora.api.subtask.service.SubTaskService;
//import kr.bora.api.todo.dto.TodoDto;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Log4j2
//public class SubTaskServiceTest {
//
//    @Autowired
//    private SubTaskService subTaskService;
//
//    @Test
//    public void testSubTaskList() {
//        Long todoId = 10L;
//        List<SubTaskDto> list = subTaskService.getList(todoId);
//
//        list.forEach(sb -> log.info(sb));
//    }
//
//    @Transactional
//    @Test
//    @Commit
//    public void testModify() {
//        SubTaskDto dto = SubTaskDto.builder()
//                .subTaskId(36L)
//                .todoId(36L)
//                .title("변경")
//                .start("변경 start")
//                .end("변경 end")
//                .assignee("변경 assignee")
//                .build();
//
//        subTaskService.modify(dto);
//    }
//}
