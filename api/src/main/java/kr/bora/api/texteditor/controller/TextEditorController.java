package kr.bora.api.texteditor.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.service.TextEditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edits")
public class TextEditorController {

    private final TextEditorService textEditorService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveEditor(@RequestPart(value = "file", required = false) List<MultipartFile> files,
                                                  @RequestPart(value = "textEditorDto") TextEditorDto.Request textEditorDto) {
        textEditorService.saveEditor(textEditorDto, files);
        return ResponseEntity.ok(ApiResponse.success("response save data", textEditorDto));
    }

    @GetMapping("/read/{textEditId}")
    public ResponseEntity<ApiResponse> readEditor(@PathVariable Long textEditId) {
        TextEditorDto.Response response = textEditorService.readEditor(textEditId);
        return ResponseEntity.ok(ApiResponse.success("response read data", response));
    }

    @PutMapping("/update/{textEditId}")
    public ResponseEntity<ApiResponse> modifyEditor(@RequestPart(value = "file", required = false) List<MultipartFile> files,
                                                    @RequestPart(value = "textEditorDto") TextEditorDto.Request textEditDto,
                                                    @PathVariable Long textEditId) {
        textEditorService.modifyEditor(textEditId, textEditDto, files);
        return ResponseEntity.ok(ApiResponse.success("response update data", textEditDto));
    }

    @DeleteMapping("/delete/{textEditId}")
    public ResponseEntity<ApiResponse> removeEditor(@PathVariable Long textEditId) {
        textEditorService.removeEditor(textEditId);
        return ResponseEntity.ok(ApiResponse.success("response delete success", "성공적으로 삭제되었습니다."));
    }
}
