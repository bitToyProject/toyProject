package kr.bora.api.oauth.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.oauth.service.OauthService;
import kr.bora.api.socialAuth.properties.AppProperties;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService service;
//
//    private final AppProperties appProperties;
//    private TokenProvider tokenProvider;
//
//    @GetMapping("/get/code")
//    public String getAccessToken(String code){
////        service.getKakaoAccessToken(code);
//        return "kakao auth server code :" + code;
//    }
//    //redirect_url
//    @GetMapping("/kakao")
//    public @ResponseBody String kakaoCallBack(@RequestParam String code){
//        return "kakao auth server code :" + code;
//    }
//
//
//
////    @GetMapping("/get/accesstoken")
////    public String getKakaoAccessToken(@RequestParam String authorizeCode) {
//////        service.get
////    }
//
//    @GetMapping("/naver")
//    public void naverLogin(HttpServletRequest request, HttpServletResponse response) {
//        String loginUrl = service.getRequestLoginUrl();
//        try {
//            response.sendRedirect(loginUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
