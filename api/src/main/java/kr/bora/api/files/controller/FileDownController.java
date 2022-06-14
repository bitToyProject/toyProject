package kr.bora.api.files.controller;

import kr.bora.api.files.dto.FileDto;
import kr.bora.api.files.service.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FileDownController {

    private final FileUtil fileUtil;

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileUtil.getFile(fileId);

        Path path = Paths.get(fileDto.getPath() + "/" + fileDto.getFilename());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        // 다운로드 파일 명 한글 깨짐 처리
                        + new String(fileDto.getOriginFilename().getBytes("UTF-8"), "ISO-8859-1") + "\"")
                .body(resource);
    }

}
