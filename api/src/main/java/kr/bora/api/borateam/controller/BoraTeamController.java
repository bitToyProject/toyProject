package kr.bora.api.borateam.controller;

import kr.bora.api.borateam.service.BoraTeamService;
import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.borateam.domain.dto.BoraTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class BoraTeamController {

    private final BoraTeamService service;

    @PostMapping("/saves/{nickname}")
    public ResponseEntity<ApiResponse> registerTeam(@RequestBody BoraTeamDto.TeamRequest command, @PathVariable String nickname){

        return ResponseEntity.ok(ApiResponse.success("팀 등록 성공", service.saveTeam(command, nickname)));
    }

    @PutMapping("/modify/{teamId}")
    public ResponseEntity<ApiResponse> modifyTeam(@PathVariable Long teamId, @RequestBody BoraTeamDto.TeamRequest teamRequest) {
        BoraTeamDto.TeamResponse response = service.modifyTeam(teamId, teamRequest);

        return ResponseEntity.ok(ApiResponse.success("팀 수정 성공", response));
    }

    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable Long teamId) {

        service.deleteTeam(teamId);

        return ResponseEntity.ok(ApiResponse.success("response delete success", teamId + "번 팀이 성공적으로 삭제되었습니다."));

    }

}
