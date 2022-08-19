package kr.bora.api.borateamuser.domain;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Audited(withModifiedFlag = true)
public class BoraTeamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bora_team_id")
    private BoraTeam boraTeam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String teamName;
}
