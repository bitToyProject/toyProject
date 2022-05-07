package kr.bora.api.upload.controller;

import kr.bora.api.upload.dto.FileUploadDto;
import kr.bora.api.upload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@CrossOrigin("*")
public class FileUploadController {

    private final FileUploadService fileUploadService;


    @PostMapping("/files")
    public ResponseEntity<List<FileUploadDto>> uploadFile( List<MultipartFile> files) {
        return ResponseEntity.ok(fileUploadService.saveFile(files));
    }
}
