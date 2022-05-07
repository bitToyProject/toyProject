package kr.bora.api.texteditor.domain.dto;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextEditorDto {

    private Long id;
    private String title;
    private String subtitle;
    private String contents;
    private EditorUserDto userId;
    private TeamDto team;

    public TextEditorDto textEditorDto() {
        Long userId = SecurityUtil.getCurrentUserId();
        return TextEditorDto.builder()
                .userId(EditorUserDto.builder().userId(userId).build())
                .title(title)
                .subtitle(subtitle)
                .contents(contents)
                .build();
    }

//    public TextEditor toEntity(TextEditorDto dto) {
//        Long userId = SecurityUtil.getCurrentUserId();
//        return TextEditor.builder()
//                .title(dto.getTitle())
//                .subtitle(dto.getSubtitle())
//                .contents(dto.getContents())
//                .user(EditorUserDto.builder().userId(userId).build())
//                .team(TeamDto.toEntity(dto.getTeam()))
//                .build();
//    }
}
