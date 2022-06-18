package kr.bora.api.texteditor.service;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.domain.Files;
import kr.bora.api.files.dto.FileDto;
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
public class TextEditorServiceImpl implements TextEditorService {

    private final TextEditorRepository textEditorRepository;

    private final FileUtil fileUtil;

    private final FileRepository fileRepository;

    @Override
    @Transactional(readOnly = true)
    public TextEditorDto readEditor(Long id) {
        TextEditor textEditor = textEditorRepository.getTextEditor(id);
        return entityEditorDto(textEditor);
    }

    @Override
    @Transactional
    public Long saveEditor(TextEditorDto dto, List<MultipartFile> multipartFiles) {

        TextEditor textEditor = dtoEditorEntity(dto);

        Long textEditorId = textEditorRepository.save(textEditor).getTextEditId();

        List<FileDto> fileDtoList = fileUtil.uploadFiles(multipartFiles, FileType.TEXT_EDITOR, textEditorId);

        if (!fileDtoList.isEmpty()) {
            for (FileDto fileDto : fileDtoList) {
                Files filesSave = fileDto.toEntity();
                fileRepository.save(filesSave);
            }
        }
        return textEditorId;
    }

    @Override
    @Transactional
    public void modifyEditor(Long id, TextEditorDto dto) {
        TextEditor existEditor = textEditorRepository.getById(id);
        changeTextEditor(dto, existEditor);
        textEditorRepository.save(existEditor);
    }

    @Override
    @Transactional
    public void removeEditor(Long id) {
        textEditorRepository.deleteById(id);
    }


    // TextEditor 수정시 필요한 메소드
    private void changeTextEditor(TextEditorDto dto, TextEditor textEditor) {
        textEditor.changeTitle(dto.getTitle());
        textEditor.changeSubtitle(dto.getSubtitle());
        textEditor.changeContents(dto.getContents());
    }

}
