package kr.bora.api.user.dto;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
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

    private String authKey;

    @Builder
    public UserRequestDto(
            Long userId,
            String username,
            String password,
            String lastName,
            String firstName,
            String nickName,
            String phoneNum,
            int gender,
            Authority authority,
            String authKey) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.authority = authority;
        this.authKey = authKey;
    }

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

    //    @Builder
//    public UserRequestDto(Long userId, String username, String password,Authority authority) {
//        this.userId = userId;
//        this.username = username;
//        this.password = password;
//        this.authority = authority;
//    }
    public User saveId(UserRequestDto dto) {

        log.info("asdasdsa" + dto.userId);
        return User.builder().userId(dto.userId).build();
    }
}