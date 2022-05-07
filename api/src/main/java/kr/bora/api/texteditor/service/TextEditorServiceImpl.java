package kr.bora.api.texteditor.service;

import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.texteditor.repository.TextEditorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TextEditorServiceImpl implements TextEditorService {

    private final TextEditorRepository textEditorRepository;

    @Override
    @Transactional(readOnly = true)
    public TextEditorDto readEditor(Long id) {
        TextEditor textEditor = textEditorRepository.getTextEditor(id);
        log.info("textEditor 읽어오기", textEditor);
        return entityEditorDto(textEditor);
    }

    @Override
    @Transactional
    public TextEditorDto saveEditor(TextEditorDto dto) {
        TextEditor textEditor = dtoEditorEntity(dto);
        textEditorRepository.save(textEditor);
        log.info("textEditor !!!저장!!!", textEditor);
        return dto;
    }

    @Override
    @Transactional
    public void modifyEditor(Long id, TextEditorDto dto) {
        TextEditor existEditor = textEditorRepository.getById(id);
//        textEditorRepository.save(dto); // 똑똑한 JPA가 알아서 해줌 (단, id값을 가지고 있어야 한다.)
        changeTextEditor(dto, existEditor);
        log.info("textEditor 수정해보자", existEditor);
        textEditorRepository.save(existEditor);
    }

    @Override
    @Transactional
    public void removeEditor(Long id) {
        textEditorRepository.deleteById(id);
        log.info("삭제 완료!!");
    }


    // TextEditor 수정시 필요한 메소드
    private void changeTextEditor(TextEditorDto dto, TextEditor textEditor) {
        textEditor.changeTitle(dto.getTitle());
        textEditor.changeSubtitle(dto.getSubtitle());
        textEditor.changeContents(dto.getContents());
    }

}
