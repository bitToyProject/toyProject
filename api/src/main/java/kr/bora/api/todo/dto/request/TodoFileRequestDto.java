package kr.bora.api.todo.dto.request;

import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoFileRequestDto {

    private Long todoFileId;
    private Long todoId;
    private TodoUserDto userId;

    private String uuid;
    private String filename;
    private String ofname;
    private String path;

    public TodoFileRequestDto toFileDto(Long todoId) {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoFileRequestDto.builder()
                .userId(TodoUserDto.builder().userId(userId).build())
                .todoId(todoId)
                .filename(filename)
                .ofname(ofname)
                .uuid(uuid)
                .path(path)
                .build();
    }

}
