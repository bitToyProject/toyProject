package kr.bora.api.upload.service;

import kr.bora.api.common.util.ModelMapperUtils;
import kr.bora.api.upload.domain.FileUpload;
import kr.bora.api.upload.dto.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    List<FileUploadDto> saveFile(List<MultipartFile> uploadFile);

    List<FileUploadDto> updateFile(List<MultipartFile> updateFile);

    void deleteFile(Long fileId);

    default FileUpload dtoTodEntity(FileUploadDto fileUploadDto) {
        FileUpload fileUpload = ModelMapperUtils.getModelMapper().map(fileUploadDto, FileUpload.class);

        return fileUpload;
    }

    default FileUploadDto entityToDto(FileUpload reviewFile){
        FileUploadDto fileUploadDto = ModelMapperUtils.getModelMapper().map(reviewFile, FileUploadDto.class);

        return fileUploadDto;
    }
}
