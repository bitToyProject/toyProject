package kr.bora.api.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UploadResultDto implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

}
