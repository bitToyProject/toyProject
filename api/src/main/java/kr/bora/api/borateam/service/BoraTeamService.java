package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.BoraTeamDto;

public interface BoraTeamService {

    BoraTeamDto.TeamResponse saveTeam(BoraTeamDto.TeamRequest dto, String nickname);

    BoraTeamDto.TeamResponse modifyTeam(Long teamId, BoraTeamDto.TeamRequest dto);

    void deleteTeam(Long teamId);
}
