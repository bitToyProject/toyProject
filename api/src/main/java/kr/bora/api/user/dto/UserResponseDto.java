package kr.bora.api.user.dto;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.domain.Avatar;
import kr.bora.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String username;

    private String nickname;

    private String firstName;

    private String lastName;

    private Integer gender;

    private String phoneNumber;

    private Avatar avatar;

    @Builder
    public UserResponseDto(String username,
                           String nickname,
                           String firstName,
                           String lastName,
                           Integer gender,
                           String phoneNumber,
                           Avatar avatar) {
        this.username = username;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUsername(),
                user.getNickName(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getPhoneNum(),
                user.getAvatar());
    }

}