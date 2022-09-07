package kr.bora.api.team.domain.dto;

import java.util.ArrayList;
import java.util.List;
import kr.bora.api.team.domain.entity.Team;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String teamName;
    private String memo;


    public static Team toEntity(TeamDto dto) {
        return Team.builder()
            .id(dto.getId())
            .teamName(dto.getTeamName())
            .memo(dto.getMemo())
            .build();
    }

    public static List<Team> toEntityList(List<TeamDto> teamDto) {
        if(teamDto == null) return null;
        List<Team> teamEntityList = new ArrayList<>();
        for(TeamDto dto:teamDto){
            teamEntityList.add(TeamDto.toEntity(dto));
        }
        return teamEntityList;
    }

}
