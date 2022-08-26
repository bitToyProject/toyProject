package kr.bora.api.user.service;


import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.exception.ErrorCode;
import kr.bora.api.common.util.RedisUtil;
import kr.bora.api.mailauth.repository.MailAuthRepository;
import kr.bora.api.notification.slack.factory.SlackFactory;
import kr.bora.api.notification.slack.service.SlackService;
import kr.bora.api.socialAuth.util.CookieUtils;
import kr.bora.api.user.domain.RefreshToken;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.*;
import kr.bora.api.user.jwt.TokenProvider;
import kr.bora.api.user.repository.RefreshTokenRepository;
import kr.bora.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

    private final MailAuthRepository mailAuthRepository;
    private final RedisUtil redisUtil;

    @Override
    public UserDto.UserResponse signup(UserDto.UserRequest userRequest) {
        boolean dupUsername = checkUsername(userRequest.getUsername());

        if (dupUsername) {
            throw new BoraException(ErrorCode.DUPLICATE_USERNAME, "아이디가 중복입니다. 다시 입력하세요");
        }
        User user = userRequest.toEntity(passwordEncoder);
        User saveUser = userRepository.save(user);

        return new UserDto.UserResponse(saveUser);
    }

    @Override
    public TokenDto login(HttpServletRequest request, HttpServletResponse response, AuthDto.LoginRequest loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequestDto.toAuthentication();
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            log.error("login requested parameter : \r\n username : {} \r\n password : {} ",
                    loginRequestDto.getUsername(), loginRequestDto.getPassword());
            log.error(e.getMessage(), e);
            SlackService slackService = SlackFactory.getSeries(HttpStatus.UNAUTHORIZED);
            slackService.postErrorToSlack(e, authenticationToken, HttpStatus.UNAUTHORIZED);
            return null;
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .username(loginRequestDto.getUsername())
                .build();

        refreshTokenRepository.save(refreshToken);
        tokenDto.setUserName(loginRequestDto.getUsername());

        // 쿠키에 저장할지 redis에 저장할지 선택
        int cookieMaxAge = (int) (THREE_DAYS_MSEC / 60);
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN); // 리프레시 토큰을 3일간 쿠키에 저장
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getValue(), cookieMaxAge);

//        redisUtil.set(authentication.getName(), refreshToken, tokenDto.getAccessTokenExpiresIn());

        return tokenDto;
    }

    @Override
    public TokenDto reIssue(HttpServletRequest request, HttpServletResponse response, TokenDto.TokenRequest tokenRequestDto) {

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName());

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // redis로 RefreshToken 업데이트
//        redisUtil.set(authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getAccessTokenExpiresIn());

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());

        refreshTokenRepository.save(newRefreshToken);

        tokenDto.setUserName(refreshToken.getUsername());

        int cookieMaxAge = (int) (THREE_DAYS_MSEC / 60);
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN); // 리프레시 토큰을 3일간 쿠키에 저장
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getValue(), cookieMaxAge);


        return tokenDto;
    }

    @Override
    public void logout(String accessToken, String refreshToken) {

        if (!tokenProvider.validateToken(accessToken)) {
            throw new BoraException(ErrorCode.UNAUTHORIZE_TOKEN, "권한이 없는 토큰 입니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String userId = authentication.getName();

        refreshTokenRepository.deleteBykey(userId);

        Long expiration = tokenProvider.remainExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout_access_token", expiration);

    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsByusername(username);
    }


}