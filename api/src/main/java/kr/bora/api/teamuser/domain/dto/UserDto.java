package kr.bora.api.teamuser.domain.dto;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private Authority authority;

    public static UserDto toUserList(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .name(user.getNickName())
                .authority(user.getAuthority())
                .build();
    }
}
