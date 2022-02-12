package kr.bora.api.userteam.controller;

import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.userteam.domain.dto.UserTeamDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommandDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class CommanderSave{
        private UserRequestDto user;
        private TeamDto team;
        UserTeamDto toDto(){
            return UserTeamDto.builder()
                .userDto(user)
                .teamDto(team)
                .build();

        }
    }

}
