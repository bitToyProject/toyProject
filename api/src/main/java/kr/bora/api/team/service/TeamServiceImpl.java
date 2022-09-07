package kr.bora.api.team.service;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeamServiceImpl implements TeamService{
    private final TeamRepository repository;

    @Override
    public TeamResponseDto registerTeam(TeamRequestDto dto) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        Assert.isTrue(!dup,"해당 이름의 팀이 이미 존재합니다.");
        Team entity = dto.toEntity(dto);
        Team result = repository.save(entity);
        return result.toResponseDto(result);
    }

}
