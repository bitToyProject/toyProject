package kr.bora.api.borateamuser.domain.dto;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoraTeamUserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoraTeamRequest {

        private String teamName;

        public BoraTeamUser toEntity(User user, BoraTeam boraTeam) {
            return BoraTeamUser.builder()
                    .teamMembers(user.getNickName())
                    .teamLeader(boraTeam.getLeaderNickname())
                    .teamName(boraTeam.getTeamName())
                    .boraTeam(boraTeam)
                    .user(user)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoraTeamUserResponse {
        private String teamName;

        private String nickname;

        private String regDate;

        public BoraTeamUserResponse(BoraTeamUser boraTeamUser) {
            this.teamName = boraTeamUser.getBoraTeam().getTeamName();
            this.nickname = boraTeamUser.getUser().getNickName();
            this.regDate = boraTeamUser.getRegDate();
        }
    }


}
