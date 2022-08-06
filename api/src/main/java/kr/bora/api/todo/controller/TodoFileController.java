package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.request.TodoFileRequestDto;
import kr.bora.api.todo.service.TodoFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class TodoFileController {

    private final TodoFileUploadService todoFileUploadService;

    @PostMapping("/files/{todoId}")
    public ResponseEntity<List<TodoFileRequestDto>> uploadFile(List<MultipartFile> files, @PathVariable("todoId") Long todoId) {
        for (MultipartFile file : files) {
            if (!file.getContentType().startsWith("image")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.ok(todoFileUploadService.saveFile(files, todoId));
    }

    @Value("${bora.upload.path}")
    private String uploadPath;

    @GetMapping("/display/{todoId}")
    public ResponseEntity<byte[]> getFile(String imgName) {
        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(imgName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = ResponseEntity.ok().headers(headers).body(FileCopyUtils.copyToByteArray(file));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @PutMapping("/update_file/{todoId}/{todoFileId}")
    public ResponseEntity<List<TodoFileRequestDto>> updateFile(List<MultipartFile> files, @PathVariable("todoId") Long todoId) {
        return ResponseEntity.ok(todoFileUploadService.saveFile(files, todoId));
    }

    @DeleteMapping("/delete_file/{todoFileId}")
    public ResponseEntity<String> deleteFile(@PathVariable("todoFileId") Long todoFileId) {
        todoFileUploadService.todoFileDelete(todoFileId);
        return ResponseEntity.ok("delete success");
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> download(String filename) throws IOException {
        // fileName 프론트에서 파일명 src 태그 클릭하면 됨
        Resource resource = new FileSystemResource(uploadPath + "/" + filename);

        String resourceName = resource.getFilename();

        String resourceOriginalName = resourceName.substring(resourceName.indexOf('_') + 1);

        HttpHeaders headers = new HttpHeaders();

        try {
            String downloadName = null;
            downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            headers.add("Content-Disposition", "attachment; filename="
                    + new String(downloadName.getBytes("UTF-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
