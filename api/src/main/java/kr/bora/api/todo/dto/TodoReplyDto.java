package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class TodoReplyDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request {

        private Long todoReplyId;

        private User userId;

        private Long todoId;

        private String text;

        private String todoReplyer;

        private String regDate;

        private String modDate;

        // 댓글 작성자는 회원의 닉네임으로 한다.
        public String setTodoReplyer(String todoReplyer) {
            this.todoReplyer = todoReplyer;
            return todoReplyer;
        }

        /* Dto -> Entity */
        public TodoReply toEntity(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoReply.builder()
                    .todo(Todo.builder().todoId(todoId).build())
                    .user(User.builder().userId(userId).build())
                    .text(text)
                    .todoReplyer(todoReplyer)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long todoReplyId;

        private Long userId;

        private Long todoId;

        private String text;

        private String todoReplyer;

        private LocalDateTime regDate;

        private LocalDateTime modDate;

        /* Entity -> Dto */
        public Response(TodoReply todoReply) {
            this.todoReplyId = todoReply.getTodoRno();
            this.todoId = todoReply.getTodo().getTodoId();
            this.userId = todoReply.getUser().getUserId();
            this.text = todoReply.getText();
            this.todoReplyer = todoReply.getTodoReplyer();
            this.regDate = todoReply.getRegDate();
            this.modDate = todoReply.getModDate();

        }
    }

}
