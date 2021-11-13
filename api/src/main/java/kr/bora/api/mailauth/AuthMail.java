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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailID;
    @Column(name = "auth_mail_key")
    private String authMailKey;
    @Column(name = "auth_mail")
    private String authMail;

    @Builder
    public AuthMail(String authMailKey,String authMail){
        this.authMailKey = authMailKey;
        this.authMail = authMail;
    }


}
