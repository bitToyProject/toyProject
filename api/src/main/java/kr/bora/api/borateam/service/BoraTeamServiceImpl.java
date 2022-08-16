package kr.bora.api.borateam.service;

import kr.bora.api.borateam.domain.dto.BoraTeamDto;
import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateam.repository.BoraTeamRepository;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoraTeamServiceImpl implements BoraTeamService {
    private final BoraTeamRepository repository;

    private final UserRepository userRepository;


    @Override
    @Transactional
    public BoraTeamDto.TeamResponse saveTeam(BoraTeamDto.TeamRequest dto, String nickname) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        Assert.isTrue(!dup, "해당 이름의 팀이 이미 존재합니다.");
        BoraTeam boraTeamEntity = dto.toEntity();
        List<User> participants = userRepository.findByNickName(nickname);
        for (User user : participants) {
            boraTeamEntity.addParticipants(user);
        }
        BoraTeam result = repository.save(boraTeamEntity);
        return new BoraTeamDto.TeamResponse(result);
    }

    @Override
    @Transactional
    public BoraTeamDto.TeamResponse modifyTeam(Long teamId, BoraTeamDto.TeamRequest dto) {
        boolean dup = repository.checkExistTeamName(dto.getTeamName());
        Assert.isTrue(!dup, "해당 이름의 팀이 이미 존재합니다.");

        BoraTeam boraTeam = repository.getById(teamId);
        boraTeam.changeTeamName(dto.getTeamName());
        boraTeam.changeMemo(dto.getMemo());

        BoraTeam result = repository.save(boraTeam);
        return new BoraTeamDto.TeamResponse(result);
    }

    @Override
    public void deleteTeam(Long teamId) {
        repository.deleteById(teamId);
    }

    private UserResponseDto getUserNickname() {
        UserResponseDto userNickname = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of).orElseThrow();
        return userNickname;
    }
}
