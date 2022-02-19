package kr.bora.api.teamuser.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TeamUsersResponseDto {
    private String teamName;
    private List<UserDto> users;
}
