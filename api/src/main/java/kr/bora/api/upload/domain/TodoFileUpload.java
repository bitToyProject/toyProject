package kr.bora.api.upload.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoFileUpload extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String uuid;

    private String path;

    @Column(name="of_name")
    private String originalFilename;

    @Column(name = "save_name")
    private String saveFilename;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;


}
