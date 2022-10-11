package kr.bora.api.texteditor.service;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TextEditorService {
    Long saveEditor(TextEditorDto.Request dto, List<MultipartFile> multipartFile);

    TextEditorDto.Response readEditor(Long textEditId);

    void modifyEditor(Long textEditId, TextEditorDto.Request dto, List<MultipartFile> multipartFile);

    void removeEditor(Long textEditId);

}
