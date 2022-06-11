package kr.bora.api.files.dto;

import kr.bora.api.files.domain.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
    private Long fileId;

    private String originFilename;

    private String filename;

    private String path;

    private String regDate;

    private String modDate;

    public File toEntity() {
        File file = File.builder()
                .fileId(fileId)
                .originFilename(originFilename)
                .filename(filename)
                .path(path)
                .build();
        return file;
    }
}
