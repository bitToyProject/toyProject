package kr.bora.api.teamuser.domain.dto;

import kr.bora.api.team.domain.dto.TeamResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamUserResponse {
    private Long id;
    private UserDto user;
    private TeamResponseDto team;

}
