package kr.bora.api.teamuser.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.teamuser.domain.entity.TeamUser;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamUsersDto {

    private List<Long> users;
    private TeamDto teamDto;


    public List<TeamUser> toEntityList(TeamUsersDto dto) {

        List<TeamUserDto> dtoList = new ArrayList<>();
        for(Long tmp : dto.getUsers()){
            TeamUserDto entity = TeamUserDto.builder().userId(tmp).teamDto(dto.getTeamDto())
                .build();
            dtoList.add(entity);
        }
        return dtoList.stream().map(TeamUserDto::toEntityList).collect(
            Collectors.toList());
    }
    public List<TeamUser> toEntityList(Long teamId, TeamUsersDto dto) {
        List<TeamUser> entityList = new ArrayList<>();
        Team team = Team.builder().id(teamId).build();

        for (Long tmp : dto.getUsers()) {
            TeamUser entity = TeamUser.builder()
                .user(User.builder().userId(tmp).build())
                .team(team)
                .build();
            entityList.add(entity);
        }
        return entityList;
    }
}
