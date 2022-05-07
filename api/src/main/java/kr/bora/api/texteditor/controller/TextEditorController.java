package kr.bora.api.texteditor.controller;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.texteditor.service.TextEditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
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

    @GetMapping("/read/{id}")
    public ResponseEntity<String> readEditor(@PathVariable Long id) {
        textEditorService.readEditor(id);
        return ResponseEntity.ok("불러오기 성공");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> modifyEditor(@PathVariable Long id, @RequestBody TextEditorDto dto) {
        textEditorService.modifyEditor(id, dto);
        return ResponseEntity.ok("업데이트 성공");
    }

    @DeleteMapping("/delete/{id}")
    public void removeEditor(@PathVariable Long id) {
        textEditorService.removeEditor(id);
    }
}
