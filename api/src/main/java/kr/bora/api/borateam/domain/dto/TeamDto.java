package kr.bora.api.borateam.domain.dto;

import kr.bora.api.borateam.domain.Team;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TeamDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TeamRequest {
        private String teamName;
        private String memo;

        public Team toEntity() {
            Long userId = SecurityUtil.getCurrentUserId();
            return Team.builder()
                    .user(User.builder().userId(userId).build())
                    .teamName(teamName)
                    .memo(memo)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TeamResponse{
        private Long id;
        private String teamName;
        private String memo;

        public TeamResponse(Team team) {
            this.id = team.getId();
            this.teamName = team.getTeamName();
            this.memo = team.getMemo();
        }
    }


}
