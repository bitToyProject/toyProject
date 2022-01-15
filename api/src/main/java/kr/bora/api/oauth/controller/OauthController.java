package kr.bora.api.oauth.controller;

import kr.bora.api.oauth.config.OauthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {

    @GetMapping("/kakao")
    public @ResponseBody String kakaoCallBack(String code){
        return "kakao auth server code :" + code;
    }
    @GetMapping("/kakao/getToken")
    public ResponseEntity<String> getKakaoAccessToken(){
        return OauthConfig.requestKakaoAccessToken();
    }

}
