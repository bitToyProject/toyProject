package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.BoraTeamDto;
import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateam.repository.BoraTeamRepository;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.repository.BoraTeamUserRepository;
import kr.bora.api.borateamuser.service.BoraTeamUserService;
import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.exception.ErrorCode;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoraTeamServiceImpl implements BoraTeamService {
    private final BoraTeamRepository repository;
    private final BoraTeamUserRepository boraTeamUserRepository;

    private final UserRepository userRepository;

    @Override
    public BoraTeamDto.TeamResponse saveTeam(BoraTeamDto.TeamRequest dto) {

        teamDupCheckValid(dto);

        UserResponseDto userNickname = getUserNickname();

        String nickname = userNickname.getNickname();

        String leader = nickname;

        BoraTeam boraTeamEntity = dto.toEntity(leader);

        BoraTeam result = repository.save(boraTeamEntity);

        // 팀을 만든 팀장은 팀 유저에도 등록 되어야 한다.
        BoraTeamUser TeamLeader = BoraTeamUser.builder()
                .user(User.builder().userId(boraTeamEntity.getLeaderId()).build())
                .boraTeam(result)
                .teamName(result.getTeamName())
                .teamMembers(nickname)
                .teamLeader(result.getLeaderNickname())
                .build();
        boraTeamUserRepository.save(TeamLeader);

        return new BoraTeamDto.TeamResponse(result);
    }

    @Override
    public BoraTeamDto.TeamResponse modifyTeam(Long teamId, BoraTeamDto.TeamRequest dto) {
        Long loginUserId = SecurityUtil.getCurrentUserId();
        Long boraTeamLeaderId = repository.getLeaderId(teamId);


        BoraTeam boraTeam = repository.getById(teamId);

        if (loginUserId == boraTeamLeaderId) {
            teamDupCheckValid(dto);
            boraTeam.changeTeamName(dto.getTeamName());
            boraTeam.changeMemo(dto.getMemo());
        } else {
            throw new IllegalArgumentException("팀장만 팀 명 또는 메모를 변경 할 수있습니다.");
        }

        BoraTeam result = repository.save(boraTeam);
        return new BoraTeamDto.TeamResponse(result);
    }

    @Override
    public void deleteTeam(Long teamId) {
        Long loginUserId = SecurityUtil.getCurrentUserId();
        Long boraTeamLeaderId = repository.getLeaderId(teamId);

        if (loginUserId == boraTeamLeaderId) {
            boraTeamUserRepository.deleteBoraTeamUserByByTeamId(teamId);
            repository.deleteById(teamId);
        } else {
            throw new IllegalArgumentException("팀장만 팀을 삭제 할 수 있습니다.");
        }
    }

    private void teamDupCheckValid(BoraTeamDto.TeamRequest dto) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        if (dup) {
            throw new BoraException(ErrorCode.EXIST_DUP_TEAM, "기존 팀 명 또는 다른 팀 이름이 이미 존재합니다.");
        }
    }


    // Todo 작성자 - 현재 사용자 닉네임
    private UserResponseDto getUserNickname() {
        UserResponseDto userNickname = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of).orElseThrow();
        return userNickname;
    }


}
