package kr.bora.api.user.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "user_history")
@Builder
public class UserHistory {

    @Id
    @Column(name = "user_id")
    private Long userId;

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

}
