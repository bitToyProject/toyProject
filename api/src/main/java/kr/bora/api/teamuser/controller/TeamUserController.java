package kr.bora.api.teamuser.controller;

import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersDto;
import kr.bora.api.teamuser.domain.dto.TeamUsersResponseDto;
import kr.bora.api.teamuser.service.TeamUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamUserController {

    private final TeamUserService service;

    /**
     * 단일 팀, 단일 팀원 생성
     * @param command
     * @return Id, User, Team Info
     */
    @PostMapping("/save")
    public ResponseEntity<TeamUserResponse> saveTeam(@RequestBody CommandDto.CommanderSave command){
        TeamUserDto dto = command.toDto();
        return ResponseEntity.ok(service.saveTeam(dto));
    }

    /**
     * 단일 팀, 다수 유저 생성
     * @param command
     * @return List<Id, User, Team> Infos
     */
    @PostMapping("/save/users")
    public ResponseEntity<List<TeamUserResponse>> saveTeamUsers(@RequestBody CommandDto.CommanderSaveUsers command) {
        TeamUsersDto dto = command.toDto();
        return ResponseEntity.ok(service.saveTeamUsers(dto));
    }

    /**
     * 팀, 팀원 조회
     * @param teamId
     * @return teamName, List<User>
     */
    @GetMapping("/find/team/{teamId}")
    public ResponseEntity<TeamUsersResponseDto> findTeamUsers(@PathVariable Long teamId) {
        return ResponseEntity.ok(service.findTeamUsers(teamId));
    }
    @PutMapping("/add/team/user/{teamId}")
    public ResponseEntity<TeamUsersResponseDto> addTeamUsers(@PathVariable Long teamId, @RequestBody CommandDto.CommanderAddUsers command) {
        TeamUsersDto dto = command.toDto();
        return ResponseEntity.ok(service.addTeamUsers(teamId, dto));
    }
}
