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
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamUserController {

    private final TeamUserService service;

    @PostMapping("/save")
    public ResponseEntity<TeamUserResponse> saveTeam(CommandDto.CommanderSave command){
        TeamUserDto dto = command.toDto();
        return ResponseEntity.ok(service.saveTeam(dto));
    }
    @PostMapping("/save/users")
    public ResponseEntity<List<TeamUserResponse>> saveTeamUsers(CommandDto.CommanderSaveUsers command) {
        TeamUsersDto dto = command.toDto();
        return ResponseEntity.ok(service.saveTeamUsers(dto));
    }
    @GetMapping("/find/team/{teamId}")
    public ResponseEntity<TeamUsersResponseDto> findTeamUsers(@PathVariable Long teamId) {
        return ResponseEntity.ok(service.findTeamUsers(teamId));
    }
}
