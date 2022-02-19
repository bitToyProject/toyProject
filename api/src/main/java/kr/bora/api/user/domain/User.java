package kr.bora.api.user.domain;

import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.user.domain.reader.MailSender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="users")
@Slf4j
@Audited(withModifiedFlag = true)
public class User{
//notnull : username , password , phonenum, fisrnma,lastname,gender
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    //username은 이메일이다.
    @Column(name="username", nullable = false, length = 50)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "last_name", nullable = false, length = 10)
    private String lastName;

    @Column(name = "first_name", nullable = false,length = 30)
    private String firstName;

    @Column(name = "nick_name", nullable = true, length = 20)
    private String nickName;

    @Column(name = "phone_num", nullable = false, length = 13)
    private String phoneNum;

    @Column(name = "gender",nullable = false)
    private int gender;

    @Column(name="authority")
    @Enumerated(EnumType.STRING) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;


    @Builder
    public User(Long userId, String username, String password, String lastName, String firstName
        , String nickName, String phoneNum, int gender, Authority authority) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.authority = authority;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickName) {
        this.nickName = nickName;
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