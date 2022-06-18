package kr.bora.api.texteditor.controller;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.service.TextEditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/edits")
public class TextEditorController {

    private final TextEditorService textEditorService;

    @PostMapping("/save")
    public ResponseEntity<String> saveEditor(@RequestPart(value = "file", required = false) List<MultipartFile> files,
                                             @RequestPart(value = "textEditorDto") TextEditorDto textEditorDto) {
        textEditorService.saveEditor(textEditorDto.textEditorDto(), files);
        return ResponseEntity.ok("성공");
    }
}
