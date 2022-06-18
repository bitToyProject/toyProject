package kr.bora.api.texteditor.domain.entity;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.texteditor.domain.dto.TextEditorDto;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "textEditor")
@Audited(withModifiedFlag = true)
public class TextEditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_edit_id", unique = true, nullable = false)
    private Long textEditId;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "contents")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_user")
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "editor_team")
//    private Team team;

    @Enumerated(EnumType.STRING)
    private FileType fileType;


    //(swagger로 알려주기)

    // 재민오빠 파일업로드 되면 붙이기
    // CRUD 가능하게끔

//    public TextEditorDto toTextEditorDto(TextEditor entity) {
//        return TextEditorDto.builder()
//                .id(entity.getId())
//                .title(entity.getTitle())
//                .subtitle(entity.getSubtitle())
//                .contents(entity.getContents())
//                .userId(UserResponseDto.of(entity.getUser()))
//                .team(TeamDto.builder().id(entity.getId()).build())
//                .build();
//    }

    // == TextEditor 수정시 변경 메소드 == //

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }


}
