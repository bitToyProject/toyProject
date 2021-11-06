package kr.bora.api.mailauth;

import kr.bora.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "auth_mail")
public class AuthMail {

    @Id
    @Column(name = "mail_id")
    private Long mailID;
    @Column(name = "auth_mail_key")
    private String authMailKey;

    @OneToOne
    @JoinColumn(name = "userId")
    User userId;

    @Builder
    public AuthMail( String authMailKey){
        this.authMailKey = authMailKey;
    }


}
