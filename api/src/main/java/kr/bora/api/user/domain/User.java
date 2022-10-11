package kr.bora.api.user.domain;

import kr.bora.api.socialAuth.domain.ProviderType;
import kr.bora.api.mailauth.reader.MailSender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="users")
@Slf4j
@Audited(withModifiedFlag = true)
public class User{
//notnull : username , password , phonenum, gender
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    //username은 이메일이다.
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = true, length = 20)
    private String nickName;

    @Column(name="authority")
    @Enumerated(EnumType.ORDINAL) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    private String oauthId;
    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickName) {
        this.nickName = nickName;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Transient
    private MailSender mailSender;

    @DomainEvents
    public MailSender publishMailEvent(){
        mailSender = new MailSender(this);
        return mailSender;
    }

    @AfterDomainEventPublication
    public void clearEvent(){
        log.info("AuthMail Send Success");
    }


}