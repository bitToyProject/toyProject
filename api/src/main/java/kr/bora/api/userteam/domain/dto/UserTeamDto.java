package kr.bora.api.userteam.domain.dto;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.user.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserTeamDto {
    private UserRequestDto userDto;
    private TeamDto teamDto;

}
