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
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "memo")
    private String memo;

    @OneToMany(mappedBy = "boraTeam", cascade = CascadeType.ALL)
    private List<User> participants = new ArrayList<>();

    public void addParticipants(User user) {
        this.participants.add(user);
        user.updateTeam(this);
    }

    public void changeTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void changeMemo(String memo) {
        this.memo = memo;
    }
}
