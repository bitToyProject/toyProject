package kr.bora.api.team.domain.dto;

import java.util.List;
import kr.bora.api.team.domain.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamResponseDto {
    private Long id;
    private String teamName;
    private String memo;
    private TeamDto superTeam;
    private List<TeamDto> subTeam;


}
