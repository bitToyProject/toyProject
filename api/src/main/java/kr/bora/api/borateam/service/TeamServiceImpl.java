package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.borateam.domain.Team;
import kr.bora.api.borateam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;


    @Override
    @Transactional
    public TeamDto.TeamResponse saveTeam(TeamDto.TeamRequest dto) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        Assert.isTrue(!dup,"해당 이름의 팀이 이미 존재합니다.");
        Team teamEntity = dto.toEntity();
        Team result = repository.save(teamEntity);
        return new TeamDto.TeamResponse(result);
    }

    @Override
    @Transactional
    public TeamDto.TeamResponse modifyTeam(Long teamId, TeamDto.TeamRequest dto) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        Assert.isTrue(!dup,"해당 이름의 팀이 이미 존재합니다.");

        Team team = repository.getById(teamId);
        team.changeTeamName(dto.getTeamName());
        team.changeMemo(dto.getMemo());

        Team result = repository.save(team);
        return new TeamDto.TeamResponse(result);
    }

    @Override
    public void deleteTeam(Long teamId) {
        repository.deleteById(teamId);
    }
}
