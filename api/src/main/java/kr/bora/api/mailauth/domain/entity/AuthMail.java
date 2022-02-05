package kr.bora.api.mailauth.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "auth_mail")
public class AuthMail {

    @Id
    @Column(name = "mail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;
    @Column(name = "auth_mail_key")
    private String authMailKey;
    @Column(name = "auth_mail")
    private String authMail;
    @Column(name = "auth_status")
    @Enumerated(value = EnumType.STRING)
    private AuthStatus authStatus;

    @Getter
    public enum AuthStatus{
        CHECKED,UNCHECKED
    }

    @Builder
    public AuthMail(String authMailKey,String authMail,AuthStatus authStatus){
        this.authMailKey = authMailKey;
        this.authMail = authMail;
        this.authStatus = authStatus;
    }


}
