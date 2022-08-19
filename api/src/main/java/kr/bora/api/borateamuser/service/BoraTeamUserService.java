package kr.bora.api.borateamuser.service;

import kr.bora.api.borateam.domain.BoraTeam;
import kr.bora.api.borateam.domain.dto.BoraTeamDto;
import kr.bora.api.borateam.repository.BoraTeamRepository;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.domain.dto.BoraTeamUserDto;
import kr.bora.api.borateamuser.repository.BoraTeamUserRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoraTeamUserService {

    private final UserRepository userRepository;

    private final BoraTeamRepository boraTeamRepository;

    private final BoraTeamUserRepository boraTeamUserRepository;


    @Transactional
    public BoraTeamUserDto.BoraTeamUserResponse teamUserSave(BoraTeamUserDto.BoraTeamRequest dto, String nickname) {
//        boolean dup = boraTeamUserRepository.checkExistNickname(nickname);
//        Assert.isTrue(!dup, "해당 팀에 이미 존재합니다.");

        BoraTeam boraTeamName = boraTeamRepository.findByTeamName(dto.getTeamName());

        List<User> participants = userRepository.findByNickName(nickname);

        BoraTeamUser TeamUserSave = dto.toEntity(participants.get(0), boraTeamName);

        BoraTeamUser result = boraTeamUserRepository.save(TeamUserSave);

        return new BoraTeamUserDto.BoraTeamUserResponse(result);
    }

}
