package kr.bora.api.borateam.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Audited(withModifiedFlag = true)
public class BoraTeam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bora_team_id")
    private Long id;

    private Long leaderId; // nickname으로 바꾸기

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "memo")
    private String memo;

    @Column(name = "leader")
    private String leaderNickname;

    public void changeTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void changeMemo(String memo) {
        this.memo = memo;
    }
}
