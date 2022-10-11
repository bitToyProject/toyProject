package kr.bora.api.team.controller;

import java.util.List;
import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TeamCommandDto {

    static class addTeamUser{
        private Long userId;
        private TeamDto team;
        TeamUserDto toDto(){
            return TeamUserDto.builder()
                .userId(userId)
                .team(team)
                .build();
        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class RegisterTeam{
        private String teamName;
        private String memo;
//        private TeamDto superTeam;
//        private List<TeamDto> subTeamList;
        TeamRequestDto toDto(){
            return TeamRequestDto.builder()
                .teamName(teamName)
                .memo(memo)
//                .superTeam(superTeam)
//                .subTeamList(subTeamList)
                .build();
        }
    }

}
