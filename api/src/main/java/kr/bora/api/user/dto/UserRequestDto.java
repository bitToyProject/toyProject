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

    private Long id;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private Authority authority;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .id(id)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Builder
    public UserRequestDto(Long id, String email, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }


}