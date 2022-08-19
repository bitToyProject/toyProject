package kr.bora.api.borateamuser.controller;

import io.swagger.annotations.Api;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.domain.dto.BoraTeamUserDto;
import kr.bora.api.borateamuser.service.BoraTeamUserService;
import kr.bora.api.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team_user")
public class BoraTeamUserController {

    private final BoraTeamUserService service;

    @PostMapping("/save/{nickname}")
    public ResponseEntity<ApiResponse> teamUsersave(@RequestBody BoraTeamUserDto.BoraTeamRequest dto, @PathVariable String nickname) {

        BoraTeamUserDto.BoraTeamUserResponse boraTeamUserResponse = service.teamUserSave(dto, nickname);

        return ResponseEntity.ok(ApiResponse.success( dto.getTeamName() + " 팀에 등록", boraTeamUserResponse));
    }

    @GetMapping("/finds/{teamId}")
    public ResponseEntity<ApiResponse> findTeamUsers(@PathVariable Long teamId) {
        List<BoraTeamUserDto.BoraTeamUserResponse> teamUsers = service.findTeamUsers(teamId);
        return ResponseEntity.ok(ApiResponse.success(teamUsers.get(0).getTeamName()+" 팀 구성원", teamUsers));
    }
}
