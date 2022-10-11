package kr.bora.api.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoFileDto {

    private Long todoFileId;
    private Long todoId;
    private TodoUserDto userId;

    private String uuid;
    private String filename;
    private String ofname;
    private String path;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
