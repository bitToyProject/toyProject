package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.request.TodoFileRequestDto;
import kr.bora.api.todo.service.TodoFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@CrossOrigin(origins ="*")
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
}
