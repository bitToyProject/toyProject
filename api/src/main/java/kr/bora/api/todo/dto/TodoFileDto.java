package kr.bora.api.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoFileDto {

    private Long todoFileId;
    private Long todoId;
    private TodoUserDto userId;

    private String uuid;
    private String imgName;
    private String path;

    private String regDate;

    private String modDate;
}
