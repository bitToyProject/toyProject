package kr.bora.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String auth;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .auth(auth)
                .build();
    }

    @Builder
    public MemberDto(Long id, String username, String password, String phoneNumber, String auth) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.auth = auth;
    }
}
