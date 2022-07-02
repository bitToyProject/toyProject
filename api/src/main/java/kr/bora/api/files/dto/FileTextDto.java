package kr.bora.api.files.dto;

import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FileTextDto {
    private Long textId;

    public TextEditor saveId(FileTextDto dto) {

        return TextEditor.builder().textEditId(dto.textId).build();
    }
}
