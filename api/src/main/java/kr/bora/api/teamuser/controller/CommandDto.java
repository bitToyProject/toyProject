package kr.bora.api.teamuser.controller;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUsersDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CommandDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class CommanderSave{
        private Long user;
        private TeamDto team;
        TeamUserDto toDto(){
            return TeamUserDto.builder()
                .userId(user)
                .teamDto(team)
                .build();

        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class CommanderSaveUsers{
        private List<Long> user;
        private TeamDto team;
        TeamUsersDto toDto(){
            return TeamUsersDto.builder()
                .users(user)
                .teamDto(team)
                .build();

        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class CommanderAddUsers{
        private List<Long> user;
        TeamUsersDto toDto(){
            return TeamUsersDto.builder()
                .users(user)
                .build();
        }
    }

}
