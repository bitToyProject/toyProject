package kr.bora.api.user.dto;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.Title;
import kr.bora.api.user.domain.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class UserRequestDto {

    private Long userId;
    @Email
//    @NotEmpty
    private String username;
    //    @NotEmpty
    private String password;
    //    @NotEmpty
    private String lastName;
    //    @NotEmpty
    private String firstName;

    private String nickName;
    //    @NotEmpty
    private String phoneNum;
    //    @NotNull
    private int gender;
    private Authority authority;

    private Title title;

    private String authKey;


    public User toUserforSave(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .username(username)
                .password(passwordEncoder.encode(password))
                .lastName(lastName)
                .firstName(firstName)
                .nickName(nickName)
                .phoneNum(phoneNum)
                .gender(gender)
                .title(Title.STARTER)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public User toUserEntity(UserRequestDto dto) {
        return User.builder()
                .userId(dto.getUserId())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .firstName(dto.getFirstName())
                .nickName(dto.getNickName())
                .authority(dto.getAuthority())
                .phoneNum(dto.getPhoneNum())
                .username(dto.getUsername())
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public User saveId(UserRequestDto dto) {

        log.info("asdasdsa" + dto.userId);
        return User.builder().userId(dto.userId).build();
    }


}