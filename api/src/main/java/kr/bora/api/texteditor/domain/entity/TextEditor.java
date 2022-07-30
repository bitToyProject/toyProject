package kr.bora.api.texteditor.domain.entity;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
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
public class TextEditor extends BaseEntity {

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
