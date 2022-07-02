package kr.bora.api.files.dto;

import kr.bora.api.files.domain.Files;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FileTodoDto {

    private Long fileId;

    public Files saveId(FileTodoDto dto) {

        return Files.builder().fileId(dto.fileId).build();
    }
}
