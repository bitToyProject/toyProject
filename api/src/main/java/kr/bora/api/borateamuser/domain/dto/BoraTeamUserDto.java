package kr.bora.api.borateamuser.domain.dto;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static kr.bora.api.user.util.SecurityUtil.userRepository;

public class BoraTeamUserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoraTeamRequest {

        private String teamName;

        public BoraTeamUser toEntity(User user, BoraTeam boraTeam) {
            return BoraTeamUser.builder()
                    .boraTeam(boraTeam)
                    .user(user)
                    .teamName(boraTeam.getTeamName())
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


        public BoraTeamUserResponse(BoraTeamUser boraTeamUser) {
            this.teamName = boraTeamUser.getBoraTeam().getTeamName();
            this.nickname = boraTeamUser.getUser().getNickName();
        }
    }
}
