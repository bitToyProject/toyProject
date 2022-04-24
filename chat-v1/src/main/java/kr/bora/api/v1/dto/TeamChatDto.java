package kr.bora.api.v1.dto;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class TeamChatDto {

    private int teamId;
    private Long userId;

    public TeamChatDto(int teamId, Long userId) {
        Assert.notNull(teamId,"[TeamChatDto] teamId must not be null");
        Assert.notNull(teamId,"[TeamChatDto] userId must not be null");

        this.teamId = teamId;
        this.userId = userId;
    }

}
