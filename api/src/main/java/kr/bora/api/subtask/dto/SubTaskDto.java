package kr.bora.api.subtask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubtaskType;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class SubTaskDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Request {
        private Todo todo;

        private User user;
        private String title;
        private String start;
        private String end;
        private String assignee;
        private Integer point;

        private SubtaskType subtaskType;

        @LastModifiedDate
        private String doneTime;
        private String regDate;
        private String modDate;

        /* Dto -> Entity */
        public SubTask toEntity(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return SubTask.builder()
                    .todo(Todo.builder().todoId(todoId).build())
                    .user(User.builder().userId(userId).build())
                    .title(title)
                    .start(start)
                    .end(end)
                    .assignee(assignee)
                    .point(point)
                    .subTaskType(SubtaskType.SUBTASK)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Long subTaskId;
        private Long todoId;
        private String title;
        private String start;

        private String end;

        private String assignee;

        private Integer point;
        private SubtaskType subTaskType;
        @LastModifiedDate
        private String doneTime;
        @JsonIgnore
        private LocalDateTime regDate;
        @JsonIgnore
        private LocalDateTime modDate;

        private String nickName;

        private Long userId;
        @JsonInclude(JsonInclude.Include.NON_EMPTY) // 댓글 목록이 비어 있을 땐 응답 값이 안보이게 해준다.
        private List<SubTaskReplyDto.Response> subTaskReplies;

        /* Entity -> Dto */
        public Response(SubTask subTask) {
            this.subTaskId = subTask.getSubTaskId();
            this.todoId = subTask.getTodo().getTodoId();
            this.title = subTask.getTitle();
            this.start = subTask.getStart();
            this.end = subTask.getEnd();
            this.assignee = subTask.getAssignee();
            this.point = subTask.getPoint();
            this.doneTime = subTask.getDoneTime();
            this.regDate = subTask.getRegDate();
            this.modDate = subTask.getModDate();
            this.subTaskType = subTask.getSubTaskType();
            this.userId = subTask.getUser().getUserId();
            this.nickName = subTask.getUser().getNickName();
            this.subTaskReplies = subTask.getSubTaskReplies().stream().map(SubTaskReplyDto.Response::new).collect(Collectors.toList());

        }
    }

}
