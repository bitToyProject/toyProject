package kr.bora.api.texteditor.controller;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.service.TextEditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/edits")
public class TextEditorController {

    private final TextEditorService textEditorService;

    @PostMapping("/save")
    public ResponseEntity<String> saveEditor(@RequestBody TextEditorDto textEditorDto) {
        textEditorService.saveEditor(textEditorDto.textEditorDto());
        return ResponseEntity.ok("성공");
    }
}
