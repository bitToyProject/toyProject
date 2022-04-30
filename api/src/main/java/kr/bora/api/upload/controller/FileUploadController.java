package kr.bora.api.upload.controller;

import kr.bora.api.upload.dto.FileUploadDto;
import kr.bora.api.upload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileUploadController  {

    private final FileUploadService uploadService;

    @Value("${bora.upload.path}")
    private String uploadPath;


    @PostMapping("/uploadFile")
    public ResponseEntity<List<FileUploadDto>> uploadFile(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            if (!file.getContentType().startsWith("image")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.ok(uploadService.saveFile(files));
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFiles(String fileName) throws IOException {

        String srcFileName = URLDecoder.decode(fileName, "UTF-8");
        File file = new File(uploadPath + File.separator + srcFileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", Files.probeContentType(file.toPath()));

        return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

    }

    @PutMapping("/update/{fileId}")
    public ResponseEntity<List<FileUploadDto>> updateFiles(List<MultipartFile> files) {
        return ResponseEntity.ok(uploadService.saveFile(files));
    }

    @DeleteMapping("/deleteFile/{todoFileId}")
    public ResponseEntity<String> deleteFile(@PathVariable("todoFileId") Long todoFileId) {

        uploadService.deleteFile(todoFileId);

        return ResponseEntity.ok("파일 삭제 성공");
    }
}
