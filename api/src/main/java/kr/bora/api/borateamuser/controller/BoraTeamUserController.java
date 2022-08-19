package kr.bora.api.borateamuser.controller;

import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.domain.dto.BoraTeamUserDto;
import kr.bora.api.borateamuser.service.BoraTeamUserService;
import kr.bora.api.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
