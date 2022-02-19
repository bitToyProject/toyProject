package kr.bora.api.teamuser.service;

import java.util.List;
import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersDto;
import kr.bora.api.teamuser.domain.dto.TeamUsersResponseDto;

public interface TeamUserService {


    TeamUserResponse saveTeam(TeamUserDto dto);

    List<TeamUserResponse> saveTeamUsers(TeamUsersDto dto);

    TeamUsersResponseDto findTeamUsers(Long teamId);
}
