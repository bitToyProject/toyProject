package kr.bora.api.upload.controller;

import kr.bora.api.upload.dto.TodoFileUploadDto;
import kr.bora.api.upload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class FileUploadController {

    private final FileUploadService uploadService;

    @Value("${bora.upload.path}")
    private String uploadPath;


    @PostMapping("/uploadFile")
    public ResponseEntity<List<TodoFileUploadDto>> uploadFile(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if (!file.getContentType().startsWith("image")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.ok(uploadService.saveFile(files));
    }
}
