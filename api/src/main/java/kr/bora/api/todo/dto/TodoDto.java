package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoPriorityType;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;
import java.util.stream.Collectors;


public class TodoDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request{
        private Long todoId;

        private User user;
        private String title;

        private String start;

        private String end;

        private String description;

        private String assignee;

        private String nickname;

        private TodoPriorityType priority;
        private Integer point = 0;

        private TodoType todoType;

        @LastModifiedDate
        private String doneTime;

        private String regDate;

        @JsonIgnore
        private String modDate;



        public String setNickname(String nickname) {
            this.nickname = nickname;
            return nickname;
        }


        public Todo toEntity() {
            Long userId = SecurityUtil.getCurrentUserId();
            return Todo.builder()
                    .user(User.builder().userId(userId).build())
                    .title(title)
                    .start(start)
                    .end(end)
                    .description(description)
                    .assignee(assignee)
                    .nickname(nickname)
                    .priority(TodoPriorityType.BASIC)
                    .point(point)
                    .todoType(TodoType.TODO)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long todoId;

        private Long userId;

        private String title;

        private String start;

        private String end;

        private String description;

        private String assignee;

        private String nickname;

        private TodoPriorityType priority;

        private Integer point = 0;

        @LastModifiedDate
        private String doneTime;

        private String regDate;

        @JsonIgnore
        private String modDate;

        private TodoType todoType;

        @JsonInclude(JsonInclude.Include.NON_EMPTY) // 댓글 목록이 비어 있을 땐 응답 값이 안보이게 해준다.
        private List<TodoReplyDto.Response> todoReplies;

        public Response(Todo todo) {
            this.todoId = todo.getTodoId();
            this.title = todo.getTitle();
            this.start = todo.getStart();
            this.end = todo.getEnd();
            this.description = todo.getDescription();
            this.assignee = todo.getAssignee();
            this.point = todo.getPoint();
            this.doneTime = todo.getDoneTime();
            this.regDate = todo.getRegDate();
            this.modDate = todo.getModDate();
            this.todoType = todo.getTodoType();
            this.priority = todo.getPriority();
            this.userId = todo.getUser().getUserId();
            this.nickname = todo.getUser().getNickName();
            this.todoReplies = todo.getTodoReplies().stream().map(TodoReplyDto.Response::new).collect(Collectors.toList());

        }
    }

}
