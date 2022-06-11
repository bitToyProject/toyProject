package kr.bora.api.files.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRequestDto {

    private Long fileId;

    private String originFilename;

    private String filename;

    private String path;

    private String regDate;

    private String modDate;
}
