package kr.bora.api.upload.service;

import kr.bora.api.upload.dto.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    List<FileUploadDto> saveFile(List<MultipartFile> uploadFiles);


}
