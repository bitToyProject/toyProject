package kr.bora.api.upload.dto;

import kr.bora.api.todo.dto.TodoUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
