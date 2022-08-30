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

import javax.annotation.RegEx;
import javax.validation.constraints.*;
import java.security.InvalidParameterException;

public class UserDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserRequest {
        private Long userId;
        @Email
        @NotBlank(message = "아이디는 필수 입력입니다.")
        private String username;
        @NotBlank(message = "비밀번호는 필수 입력입니다.")
        @Pattern(regexp = "^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
                message = "비밀번호는 8~20자리의 숫자,문자,특수문자로 이루어져야합니다.")
        private String password;
        private String nickName;
        @NotBlank
        @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
        private String phoneNum;

        private Authority authority;

        private Title title;
        private Avatar avatar;

        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .nickName(nickName)
                    .phoneNum(phoneNum)
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
        private String phoneNumber;
        private Avatar avatar;
        private ProviderType providerType;

        public UserResponse(User user) {
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.nickname = user.getNickName();
            this.phoneNumber = user.getPhoneNum();
            this.avatar = user.getAvatar();
            this.providerType = user.getProviderType();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userInfoModify {
        @NotBlank(message = "새로운 password값이 필수 있니다.")
        @Pattern(regexp = "^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
                message = "비밀번호는 8~20자리의 숫자,문자,특수문자로 이루어져야합니다.")
        private String password;
        @NotBlank(message = "새로운 passwordCheck값이 필수 입니다.")
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
    public static class userDelete {
        public static UserDto.UserResponse deleteUserId() {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserResponse.builder()
                    .userId(currentUserId)
                    .build();
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

}
