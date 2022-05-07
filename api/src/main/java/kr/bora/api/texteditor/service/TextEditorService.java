package kr.bora.api.texteditor.service;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import org.springframework.transaction.annotation.Transactional;

public interface TextEditorService {
    TextEditorDto saveEditor(TextEditorDto dto);

    TextEditorDto readEditor(Long id);

    void modifyEditor(Long id, TextEditorDto dto);

    void removeEditor(Long id);


    default TextEditorDto entityEditorDto(TextEditor textEditor) {
        TextEditorDto users = TextEditorDto.builder().build();
        return TextEditorDto.builder()
                .id(textEditor.getId())
                .userId(users.textEditorDto().getUserId())
                .title(textEditor.getTitle())
                .subtitle(textEditor.getSubtitle())
                .contents(textEditor.getContents())
                .build();
    }

    default TextEditor dtoEditorEntity(TextEditorDto dto) {
        return TextEditor.builder()
                .title(dto.getTitle())
                .subtitle(dto.getSubtitle())
                .contents(dto.getContents())
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .build();
    }

}
