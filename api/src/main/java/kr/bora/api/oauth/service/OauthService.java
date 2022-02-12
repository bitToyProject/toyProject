package kr.bora.api.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface OauthService {

    String getKakaoAccessToken(String code);

    String getAuthCode();
}
