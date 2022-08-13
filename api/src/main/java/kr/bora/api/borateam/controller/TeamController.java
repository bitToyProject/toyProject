package kr.bora.api.borateam.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.borateam.domain.dto.TeamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TeamController {

    @PostMapping("/saves")
    public ResponseEntity<ApiResponse> registerTeam(@RequestBody TeamDto.TeamRequest command){

        return ResponseEntity.ok(ApiResponse.success("팀 등록 성공", service.saveTeam(command)));
    }

    @PutMapping("/modify/{teamId}")
    public ResponseEntity<ApiResponse> modifyTeam(@PathVariable Long teamId, @RequestBody TeamDto.TeamRequest teamRequest) {
        TeamDto.TeamResponse response = service.modifyTeam(teamId, teamRequest);

        return ResponseEntity.ok(ApiResponse.success("팀 수정 성공", response));
    }

    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable Long teamId) {

        service.deleteTeam(teamId);

        return ResponseEntity.ok(ApiResponse.success("response delete success", teamId + "번 팀이 성공적으로 삭제되었습니다."));

    }

}
