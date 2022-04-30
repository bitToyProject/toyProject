package kr.bora.api.upload.dto;

import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {

    private Long fileId;
    private String uuid;
    private String originalFilename;
    private String saveFilename;
    private String path;

    private TodoUserDto userId;
    private List<FileUploadDto> files;

    public FileUploadDto toDto() {
        Long userId = SecurityUtil.getCurrentUserId();
        return FileUploadDto.builder()
                .uuid(UUID.randomUUID().toString())
                .originalFilename(originalFilename)
                .saveFilename(saveFilename)
                .path(path)
                .userId(TodoUserDto.builder().userId(userId).build())
                .files(files)
                .build();
    }

    public void setFiles(List<FileUploadDto> files) {
        this.files = files;
    }
}
