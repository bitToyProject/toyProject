package kr.bora.api.borateam.domain.dto;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.*;

import java.util.List;


public class BoraTeamDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TeamRequest {
        private String teamName;
        private String memo;


        public BoraTeam toEntity(String leader) {
            Long userId = SecurityUtil.getCurrentUserId();
            return BoraTeam.builder()
                    .leaderId(userId)
                    .teamName(teamName)
                    .leaderNickname(leader)
                    .memo(memo)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TeamResponse {
        private Long id;
        private String teamName;

        private String teamLeader;
        private String memo;

        public TeamResponse(BoraTeam boraTeam) {
            this.id = boraTeam.getId();
            this.teamName = boraTeam.getTeamName();
            this.memo = boraTeam.getMemo();
            this.teamLeader = boraTeam.getLeaderNickname();
        }
    }


}
