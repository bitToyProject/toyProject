package kr.bora.api.oauth.controller;

import kr.bora.api.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService service;

    @GetMapping("/get/code")
    public String getAccessToken(String code){
//        service.getKakaoAccessToken(code);
        return "kakao auth server code :" + code;
    }
    //redirect_url
    @GetMapping("/kakao")
    public @ResponseBody String kakaoCallBack(@RequestParam String code){
        return "kakao auth server code :" + code;
    }
//    @GetMapping("/get/accesstoken")
//    public String getKakaoAccessToken(@RequestParam String authorizeCode) {
////        service.get
//    }


}
