package kr.bora.api.files.controller;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.dto.FileDto;
import kr.bora.api.files.service.FileUtil;
import kr.bora.api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FilesController {

    private final FileUtil fileUtil;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @PostMapping("/save")
    public ResponseEntity<String> filesSave(@RequestPart("files") List<MultipartFile> multipartFiles, FileType fileType) {

        if (fileType != null) {

            fileUtil.uploadFiles(multipartFiles, fileType);
        }

        return ResponseEntity.ok("ok");
    }


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

    @GetMapping("/display/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileId") Long fileId, @RequestParam FileType fileType) {
        ResponseEntity<byte[]> result = null;

        FileDto fileDto = fileUtil.getFile(fileId);

        String filename = fileDto.getFilename();

        try {
            String srcFileName = URLDecoder.decode(filename, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = ResponseEntity.ok().headers(headers).body(FileCopyUtils.copyToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
