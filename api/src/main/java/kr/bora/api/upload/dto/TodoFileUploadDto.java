package kr.bora.api.upload.dto;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoFileUploadDto {

    private Long fileId;
    private String uuid;
    private String originalFilename;
    private String saveFilename;
    private String imgName;
    private String path;
    private TodoUserDto userId;
    private Long todoId;

    private TodoDto todo;

    private List<TodoFileUploadDto> files;

    public TodoFileUploadDto toDto() {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoFileUploadDto.builder()
                .userId(TodoUserDto.builder().userId(userId).build())
                .todoId(todo.getTodoId())
                .files(files)
                .build();
    }

    public void setFiles(List<TodoFileUploadDto> files) {
        this.files = files;
    }
}
