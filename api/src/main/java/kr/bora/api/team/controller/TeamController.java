package kr.bora.api.team.controller;

import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService service;

    @PostMapping("/save")
    public ResponseEntity<TeamResponseDto> registerTeam(TeamCommandDto.RegisterTeam command){
        TeamRequestDto dto = command.toDto();
        return ResponseEntity.ok(service.registerTeam(dto));
    }
//    @PostMapping("/enrollment")
//    public ResponseEntity<TeamResponseDto> enrollmentUser(TeamCommandDto.)

}
