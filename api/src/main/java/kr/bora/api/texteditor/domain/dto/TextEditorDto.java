package kr.bora.api.texteditor.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TextEditorDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String title;
        private String subtitle;
        private String contents;

        /* Dto -> Entity */
        public TextEditor toEntity() {
            Long userId = SecurityUtil.getCurrentUserId();
            return TextEditor.builder()
                    .title(title)
                    .subtitle(subtitle)
                    .contents(contents)
                    .user(User.builder().userId(userId).build())
                    .build();
        }

    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long textEditId;
        private String title;
        private String subtitle;
        private String contents;
        @JsonIgnore
        private String regDate;
        @JsonIgnore
        private String modDate;

        /* Dto -> Entity */
        public Response(TextEditor textEditor) {
            this.textEditId = textEditor.getTextEditId();
            this.title = textEditor.getTitle();
            this.subtitle = textEditor.getSubtitle();
            this.contents = textEditor.getContents();
            this.regDate = textEditor.getRegDate();
            this.modDate = textEditor.getModDate();
        }

    }


}
