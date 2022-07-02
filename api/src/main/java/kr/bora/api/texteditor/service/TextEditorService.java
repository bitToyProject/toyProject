package kr.bora.api.texteditor.service;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TextEditorService {
    Long saveEditor(TextEditorDto dto, List<MultipartFile> multipartFile);

    TextEditorDto readEditor(Long id);

    void modifyEditor(Long id, TextEditorDto dto);

    void removeEditor(Long id);


    default TextEditorDto entityEditorDto(TextEditor textEditor) {
        TextEditorDto users = TextEditorDto.builder().build();
        return TextEditorDto.builder()
                .id(textEditor.getTextEditId())
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
                .fileType(FileType.TEXT_EDITOR)
                .build();
    }

}
