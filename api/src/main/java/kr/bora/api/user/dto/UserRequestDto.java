package kr.bora.api.user.dto;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.security.InvalidParameterException;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    private Long userId;
    @Email
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Authority authority;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Builder
    public UserRequestDto(Long userId, String username, String password, Authority authority) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }


}