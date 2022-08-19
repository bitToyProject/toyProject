package kr.bora.api.teamuser.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersDto;
import kr.bora.api.teamuser.domain.dto.TeamUsersResponseDto;
import kr.bora.api.teamuser.domain.entity.TeamUser;
import kr.bora.api.teamuser.repository.TeamUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TeamUserServiceImpl implements TeamUserService{

    private final TeamUserRepository repository;

    @Override
    public TeamUserResponse saveTeam(TeamUserDto dto) {
        log.info("새로운 팀 생성 (단일 유저)");
        TeamUser entity = dto.toEntity(dto);
        TeamUser result = repository.save(entity);
        log.info("생성된 팀 : {}", result);
        return result.toResponse(result);
    }

    @Override
    public List<TeamUserResponse> saveTeamUsers(TeamUsersDto dto) {
        log.info("새로운 팀 생성 (멀티 유저)");
        List<TeamUser> entity = dto.toEntityList(dto);
        List<TeamUser> result = repository.saveAll(entity);
        log.info("생성된 팀 : {}",result);
        return result.stream().map(TeamUser::toResponseList).collect(Collectors.toList());
    }

    @Override
    public TeamUsersResponseDto findTeamUsers(Long teamId) {
        log.info("팀, 팀원 조회시작");
        List<TeamUser> teamUserEntityList = repository.findTeamUsersByTeamId(teamId);
        TeamUsersResponseDto result = TeamUser.toTeamUsersResponse(teamUserEntityList);
        log.info("조회된 팀, 팀원 : {}",result);
        return result;
    }

    @Override
    public TeamUsersResponseDto addTeamUsers(Long teamId, TeamUsersDto dto) {
        log.info("팀원 추가 시작");
        List<TeamUser> teamUserEntityList = dto.toEntityList(teamId, dto);
        TeamUsersResponseDto result = TeamUser.toTeamUsersResponse(teamUserEntityList);
        log.info("추가된 팀원 : {}",result);
        return result;
    }
}
