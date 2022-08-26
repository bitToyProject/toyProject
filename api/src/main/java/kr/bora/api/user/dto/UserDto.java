package kr.bora.api.user.dto;

import kr.bora.api.socialAuth.domain.ProviderType;
import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.Avatar;
import kr.bora.api.user.domain.Title;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;

public class UserDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserRequest {
        private Long userId;
        @Email
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
        private String nickName;
        @NotEmpty
        private String phoneNum;
        @NotNull
        private String gender;

        private Authority authority;

        private Title title;
        private Avatar avatar;

        public User toEntity(PasswordEncoder passwordEncoder) {
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
                    .avatar(avatar)
                    .providerType(ProviderType.LOCAL)
                    .build();
        }

    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private Long userId;
        private String username;
        private String nickname;
        private String firstName;
        private String lastName;
        private String gender;
        private String phoneNumber;
        private Avatar avatar;
        private ProviderType providerType;
        public UserResponse(User user) {
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.nickname = user.getNickName();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNum();
            this.avatar = user.getAvatar();
            this.providerType = user.getProviderType();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MailTempPwdDto {
        private String toAddress;
        private String title;
        private String message;
        private String fromAddress;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userInfoModify {
        @NotNull(message = "새로운 password값이 필수 있니다.")
        private String password;
        @NotNull(message = "새로운 passwordCheck값이 필수 입니다.")
        private String checkPassword;

        private String nickname;


        public UserDto.UserRequest changeUserInfo() {
            if (!password.equals(checkPassword)) {
                throw new InvalidParameterException("새로운 비밀번호와 비밀번호 확인 값이 같지 않습니다.");
            }

            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserRequest.builder()
                    .userId(currentUserId)
                    .password(password)
                    .nickName(nickname)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
    public static class userDelete {
        public static UserDto.UserResponse deleteUserId() {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserResponse.builder()
                    .userId(currentUserId)
                    .build();
        }

    }

}
