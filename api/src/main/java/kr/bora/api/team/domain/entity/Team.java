package kr.bora.api.team.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited(withModifiedFlag = true)
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "memo")
    private String memo;

    @OneToMany
    @NotAudited
    private List<Team> subTeamList;

    @ManyToOne
    @NotAudited
    private Team superTeam;


    public TeamResponseDto toResponseDto(Team team){
        return TeamResponseDto.builder()
            .id(team.getId())
            .teamName(team.getTeamName())
            .memo(team.getMemo())
            .subTeam(teamDtoList(team.getSubTeamList()))
            .superTeam(toDto(team.getSuperTeam()))
            .build();
    }
    static List<TeamDto> teamDtoList(List<Team> teamList){
        List<TeamDto> teamDtoList = new ArrayList<>();
        for(Team team : teamList){
            teamDtoList.add(toDto(team));
        }
        return teamDtoList;
    }

    static TeamDto toDto(Team team) {
        return TeamDto.builder()
            .id(team.getId())
            .teamName(team.getTeamName())
            .memo(team.getMemo())
            .build();
    }


}
