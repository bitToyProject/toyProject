package kr.bora.api.upload.dto;

import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadDto {

    private String uuid;

    private String imgName;

    private String path;

    private User user;
}
