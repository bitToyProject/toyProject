package kr.bora.api.user.dto;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.socialAuth.domain.ProviderType;
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

    private String phoneNumber;

    private Avatar avatar;

    private ProviderType providerType;

    @Builder
    public UserResponseDto(String username,
                           String nickname,
                           String phoneNumber,
                           Avatar avatar,
                           ProviderType providerType) {
        this.username = username;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.providerType = providerType;
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUsername(),
                user.getNickName(),
                user.getPhoneNum(),
                user.getAvatar(),
                user.getProviderType());
    }

}