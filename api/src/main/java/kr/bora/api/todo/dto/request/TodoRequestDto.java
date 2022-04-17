package kr.bora.api.todo.dto.request;

import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.upload.dto.TodoFileUploadDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoRequestDto {
    private Long todoId;

    private TodoUserDto userId;
    @NotNull(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotNull(message = "시작일은 필수 입력 값입니다.")
    private String start;
    @NotNull(message = "종료일은 필수 입력 값입니다.")
    private String end;
    @NotNull(message = "상세 할 일은 필수 입력 값입니다.")
    private String description;
    private String assignee;
    private Integer priority;
    private Integer point = 0;

    private String doneTime;

    private String regDate;

    private String modDate;
    private String nickname;

    private TodoType todoType;

    private TodoFileUploadDto fileUpload;

    private List<TodoFileUploadDto> files;


     public TodoRequestDto toDto() {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoRequestDto.builder()
                .userId(TodoUserDto.builder().userId(userId).build())
                .title(title)
                .start(start)
                .end(end)
                .description(description)
                .assignee(assignee)
                .priority(priority)
                .point(point)
                .todoType(TodoType.TODO)
                .files(files)
                .build();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}
