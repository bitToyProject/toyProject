package kr.bora.api.teamuser.domain.entity;

import io.jsonwebtoken.lang.Assert;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersResponseDto;
import kr.bora.api.teamuser.domain.dto.UserDto;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team_user")
public class TeamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "team")
    private Team team;


    public TeamUserResponse toResponse(TeamUser result) {
        return TeamUserResponse.builder()
            .id(result.getId())
            .team(TeamResponseDto.builder().id(result.getTeam().getId())
                .teamName(result.getTeam().getTeamName())
                .build())
            .user(UserResponseDto.of(result.getUser())).build();
    }
    public static TeamUserResponse toResponseList(TeamUser entity) {
        return entity.toResponse(entity);
    }

    public static TeamUsersResponseDto toTeamUsersResponse(List<TeamUser> entityList) {
        Assert.notEmpty(entityList);

        String teamName = null;
        List<UserDto> userList = null;
        for(TeamUser entity : entityList){
            teamName = entity.getTeam().getTeamName();
            userList.add(UserDto.toUserList(entity.getUser()));
        }
        return TeamUsersResponseDto.builder()
                .teamName(teamName)
                .users(userList)
                .build();
    }
}
