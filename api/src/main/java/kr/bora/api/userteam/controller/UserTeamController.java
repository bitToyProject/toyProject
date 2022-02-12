package kr.bora.api.userteam.controller;

import kr.bora.api.userteam.domain.dto.UserTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class UserTeamController {

//    private final UserTeamService service;

//    @PostMapping("/save")
//    public ResponseEntity<UserTeamResponseDto> saveTeam(CommandDto.CommanderSave command){
//        UserTeamDto dto = command.toDto();
//        return ResponseEntity.ok(service.saveTeam(dto));
//
//    }
}
