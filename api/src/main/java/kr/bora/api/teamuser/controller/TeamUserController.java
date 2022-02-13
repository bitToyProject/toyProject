package kr.bora.api.teamuser.controller;

import java.util.List;
import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersDto;
import kr.bora.api.teamuser.service.TeamUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
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
}
