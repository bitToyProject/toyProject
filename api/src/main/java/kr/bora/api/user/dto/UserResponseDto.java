package kr.bora.api.user.dto;

import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String username;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUsername());
    }

}