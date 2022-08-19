package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.BoraTeamDto;

import java.util.List;

public interface BoraTeamService {

    BoraTeamDto.TeamResponse saveTeam(BoraTeamDto.TeamRequest dto);

    BoraTeamDto.TeamResponse modifyTeam(Long teamId, BoraTeamDto.TeamRequest dto);

    void deleteTeam(Long teamId);

}
