package kr.bora.api.team.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamUserDto {
    private Long userId;
    private TeamDto team;



}
