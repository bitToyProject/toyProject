package kr.bora.api.controller;

import kr.bora.api.domain.MemberDto;
import kr.bora.api.response.Response;
import kr.bora.api.service.JwtUserDetailsService;
import kr.bora.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final JwtUserDetailsService memberService;

    @PostMapping("/signup")
    public Response signup(@RequestBody MemberDto memberDto) { // 회원 추가
        Response response = new Response();

        try {
            memberService.save(memberDto);
            response.setResponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("회원가입을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }
        return response;
    }


}
