package kr.bora.api.files.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.texteditor.domain.entity.TextEditor;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
@Audited(withModifiedFlag = true)
public class Files extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String originFilename;

    private String filename;

    private String path;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "textEditId")
//    public TextEditor textEditor;

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}
