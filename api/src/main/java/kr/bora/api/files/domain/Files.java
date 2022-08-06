package kr.bora.api.files.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
@Table(name = "files")
@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Audited(withModifiedFlag = true)
@ToString(exclude = {"user"})
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

    private Long todoId;

    private Long textId;

    private String deleteYn;

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}
