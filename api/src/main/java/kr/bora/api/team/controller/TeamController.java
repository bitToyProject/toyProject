package kr.bora.api.team.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.team.domain.dto.TeamDto;
import kr.bora.api.team.domain.dto.TeamRequestDto;
import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService service;

    @PostMapping("/save")
    public ResponseEntity<TeamResponseDto> registerTeam(@RequestBody TeamCommandDto.RegisterTeam command){
        TeamRequestDto dto = command.toDto();
        return ResponseEntity.ok(service.registerTeam(dto));
    }

//    @PostMapping("/enrollment")
//    public ResponseEntity<TeamResponseDto> enrollmentUser(TeamCommandDto.)

}
