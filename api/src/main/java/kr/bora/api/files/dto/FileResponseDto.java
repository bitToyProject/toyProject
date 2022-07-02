package kr.bora.api.files.dto;

import kr.bora.api.files.domain.Files;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDto {

    private Long fileId;

    public static FileResponseDto of(Files entity) {
        return new FileResponseDto(entity.getFileId());
    }
}
