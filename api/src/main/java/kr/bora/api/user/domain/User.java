package kr.bora.api.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="authority")
    @Enumerated(EnumType.STRING) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;

    @Builder
    public User(Long id, String email, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}