package kr.bora.api.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoFileUploadDto {

    private String uuid;

    private String imgName;

    private String path;

}
