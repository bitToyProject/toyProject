package kr.bora.api.borateamuser.service;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateam.domain.dto.BoraTeamDto;
import kr.bora.api.borateam.repository.BoraTeamRepository;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.domain.dto.BoraTeamUserDto;
import kr.bora.api.borateamuser.repository.BoraTeamUserRepository;
import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.exception.ErrorCode;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoraTeamUserService {

    private final UserRepository userRepository;

    private final BoraTeamRepository boraTeamRepository;

    private final BoraTeamUserRepository boraTeamUserRepository;

    @Transactional
    public BoraTeamUserDto.BoraTeamUserResponse teamUserSave(BoraTeamUserDto.BoraTeamRequest dto, String nickname) {

        Long dupCnt = boraTeamUserRepository.checkExistNickname(nickname, dto.getTeamName());

        if (dupCnt > 0) {
            throw new BoraException(ErrorCode.EXIST_DUP_TEAM_USER, "해당 팀에 이미 소속되어 있습니다.");
        }

        BoraTeam boraTeamName = boraTeamRepository.findByTeamName(dto.getTeamName());

        List<User> participants = userRepository.findByNickName(nickname);

        BoraTeamUser TeamUserSave = dto.toEntity(participants.get(0), boraTeamName);

        BoraTeamUser result = boraTeamUserRepository.save(TeamUserSave);

        return new BoraTeamUserDto.BoraTeamUserResponse(result);
    }

    @Transactional
    public void deleteTeamUser(String nickname) {
        Long loginUserId = SecurityUtil.getCurrentUserId();
        BoraTeam leader = boraTeamRepository.getLeader(loginUserId);

        if (!nickname.equals(leader.getLeaderNickname())) {
            boraTeamUserRepository.deleteBoraTeamUser(nickname, leader.getTeamName());
        } else {
            throw new BoraException(ErrorCode.NOT_TEAM_OUT_USER, "팀장 본인은 강퇴 시킬 수 없습니다. 팀을 삭제하세요");
        }
    }

    public List<BoraTeamUserDto.BoraTeamUserResponse> findTeamUsers(Long teamId) {

        List<BoraTeamUser> boraTeamUsers = boraTeamUserRepository.findBoraTeamUserByBoraTeamId(teamId);

        return boraTeamUsers.stream().map(BoraTeamUserDto.BoraTeamUserResponse::new).collect(Collectors.toList());
    }


}
