package kr.bora.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private List<String> roles;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public User toEntity(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .roles(roles)
                .build();
    }

    @Builder
    public UserDto(Long id, String username, String password, String phoneNumber, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }
}
