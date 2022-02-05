package kr.bora.api.oauth.controller;

import kr.bora.api.oauth.config.OauthConfig;
import kr.bora.api.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService service;

    @GetMapping ("/code")
    public String getCode(){
        return service.getAuthCode();
    }
    @GetMapping("/accesstoken")
    public String getAccessToken(String code){
        service.getKakaoAccessToken(code);
        return "kakao auth server code :" + code;
    }
    //redirect_url
    @GetMapping("/kakao")
    public @ResponseBody String kakaoCallBack(@RequestParam String code){
        return "kakao auth server code :" + code;
    }

}
