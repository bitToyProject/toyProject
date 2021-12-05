//package kr.bora.api.repository;
//
//import kr.bora.api.subtask.domain.SubTask;
//import kr.bora.api.subtask.repository.SubTaskRepository;
//import kr.bora.api.todo.domain.Todo;
//import kr.bora.api.user.domain.User;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//@Log4j2
//public class SubTaskRepositoryTests {
//
//    @Autowired
//    private SubTaskRepository repository;
//
//    @Test
//    public void insertSubTask() {
//        IntStream.rangeClosed(1, 99).forEach(i -> {
//            long todoId = (long) (Math.random() * 98) + 1;
//
//            Todo todo = Todo.builder()
//                    .todoId(todoId).build();
//
//            SubTask subTask = SubTask.builder()
//                    .title("subtask" + i)
//                    .start("start" + i)
//                    .end("end" + i)
//                    .assignee("assignee" + i)
//                    .todo(todo)
//                    .build();
//
//            repository.save(subTask);
//        });
//    }
//
//    @Test
//    public void testGetTodoSubtasks() {
//        Todo todo = Todo.builder()
//                .todoId(9L).build();
//
//        List<SubTask> result = repository.getSubTasksByTodoOrderByRegDate(todo);
//
//        result.forEach(todoSubTask -> {
//            log.info(todoSubTask.getSubTaskId());
//            log.info(todoSubTask.getTitle());
//            log.info(todoSubTask.getStart());
//            log.info(todoSubTask.getEnd());
//            log.info(todoSubTask.getAssignee());
//        });
//    }
//
//
//}
