package kr.bora.api.texteditor.service;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.repository.FileRepository;
import kr.bora.api.files.service.FileUtil;
import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.texteditor.repository.TextEditorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextEditorServiceImpl implements TextEditorService {

    private final TextEditorRepository textEditorRepository;

    private final FileUtil fileUtil;

    private final FileRepository fileRepository;

    @Override
    public TextEditorDto.Response readEditor(Long textEditId) {

        TextEditor textEditor = textEditorRepository.findById(textEditId)
                .orElseThrow(() -> new IllegalArgumentException("텍스트 에디터를 열 수 없습니다. " + textEditId));

        return new TextEditorDto.Response(textEditor);
    }

    @Override
    @Transactional
    public Long saveEditor(TextEditorDto.Request dto, List<MultipartFile> multipartFiles) {

        TextEditor textEditor = dto.toEntity();

        textEditorRepository.save(textEditor);

        Long textEditorId = textEditorRepository.save(textEditor).getTextEditId();

        // 파일 저장
        if (textEditorId != null) {
            fileUtil.uploadFiles(multipartFiles, FileType.TEXT_EDITOR, null, textEditorId);
        }

        return textEditorId;
    }

    @Override
    @Transactional
    public void modifyEditor(Long textEditId, TextEditorDto.Request dto, List<MultipartFile> multipartFile) {
        TextEditor existEditor = textEditorRepository.getById(textEditId);

        if (existEditor.getTextEditId() != null) {
            fileUtil.updateFiles(multipartFile, FileType.TEXT_EDITOR, null, existEditor.getTextEditId(), null);
        }

        changeTextEditor(dto, existEditor);

        textEditorRepository.save(existEditor);
    }

    @Override
    @Transactional
    public void removeEditor(Long textEditId) {
        fileRepository.textFilesDelete(textEditId); // 파일 먼저 삭제
        textEditorRepository.deleteById(textEditId);
    }


    // TextEditor 수정시 필요한 메소드
    private void changeTextEditor(TextEditorDto.Request dto, TextEditor textEditor) {
        textEditor.changeTitle(dto.getTitle());
        textEditor.changeSubtitle(dto.getSubtitle());
        textEditor.changeContents(dto.getContents());
    }

}
