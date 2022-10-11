package kr.bora.api.team.domain.dto;

import java.util.ArrayList;
import java.util.List;
import kr.bora.api.team.domain.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamRequestDto {

    private Long id;
    private String teamName;
    private String memo;
//    private TeamDto superTeam;
//    private List<TeamDto> subTeamList;


    public Team toEntity(TeamRequestDto dto) {
        if(dto == null) return null;
        return Team.builder()
            .teamName(dto.getTeamName())
            .memo(dto.getMemo())
//            .subTeamList(TeamDto.toEntityList(dto.getSubTeamList()))
//            .superTeam(TeamDto.toEntity(dto.getSuperTeam()))
            .build();
    }

}
