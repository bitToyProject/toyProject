package kr.bora.api.teamuser.domain.dto;

import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamUserResponse {
    private Long id;
    private UserResponseDto userResponseDto;
    private UserDto user;
    private TeamResponseDto team;

}
