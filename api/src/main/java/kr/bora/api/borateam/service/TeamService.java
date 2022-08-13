package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;

public interface TeamService {

    TeamDto.TeamResponse saveTeam(TeamDto.TeamRequest dto);

    TeamDto.TeamResponse modifyTeam(Long teamId, TeamDto.TeamRequest dto);

    void deleteTeam(Long teamId);
}
