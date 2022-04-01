package kr.bora.api.user.domain;

import kr.bora.api.user.domain.reader.MailSender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
    private Integer gender;

    @Column(name = "indi_title",nullable = false)
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column(name="authority")
    @Enumerated(EnumType.STRING) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;

//    @Column(name = "department")
//    @Enumerated(EnumType.STRING)
//    private Department department;
//    @Column(name = "part",)
//    @Enumerated(EnumType.STRING)
//    private Part part;


    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickName) {
        this.nickName = nickName;
    }

    public void changeFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void changeLastName(String lastName) {
        this.lastName = lastName;
    }

    public void changeGender(Integer gender) {
        this.gender = gender;
    }

    public void changePhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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