package kr.bora.api.files.dto;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.domain.Files;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FileDto {
    private Long fileId;
    private String originFilename;

    private String filename;

    private String path;

    private String regDate;

    private String modDate;

    private User userId;

    //    @Nullable
    private Long todoId;

    private Long textEditorId;
//    @Nullable

    private FileType fileType;

    private String deleteType;


    public Files toEntity() {
        Files files = Files.builder()
                .fileId(fileId)
                .originFilename(originFilename)
                .filename(filename)
                .path(path)
                .fileType(fileType)
                .user(userId)
                .todoId(todoId)
                .textId(textEditorId)
                .deleteYn(deleteType)
                .build();
        return files;
    }


}
