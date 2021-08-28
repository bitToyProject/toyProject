package kr.bora.api.dto;

import kr.bora.api.domain.Authority;
import kr.bora.api.domain.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String email;
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