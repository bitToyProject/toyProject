package kr.bora.api.team.service;

import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;

public interface TeamService {

    TeamResponseDto registerTeam(TeamRequestDto dto);
}
