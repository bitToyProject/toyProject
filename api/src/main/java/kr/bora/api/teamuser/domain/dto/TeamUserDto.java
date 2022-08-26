package kr.bora.api.teamuser.domain.dto;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.teamuser.domain.entity.TeamUser;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamUserDto {
    private Long userId;
    private TeamDto teamDto;

    public TeamUser toEntity(TeamUserDto dto) {
        return TeamUser.builder()
            .team(TeamDto.toEntity(dto.getTeamDto()))
            .user(User.builder().userId(dto.getUserId()).build()).build();
    }
    public static TeamUser toEntityList(TeamUserDto dto) {
        return TeamUser.builder()
            .team(TeamDto.toEntity(dto.getTeamDto()))
            .user(User.builder().userId(dto.getUserId()).build()).build();
    }
}
