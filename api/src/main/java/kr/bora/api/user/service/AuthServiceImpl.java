package kr.bora.api.user.service;


import kr.bora.api.mailauth.repository.MailAuthRepository;
import kr.bora.api.notification.slack.factory.SlackFactory;
import kr.bora.api.notification.slack.service.SlackService;
import kr.bora.api.socialAuth.properties.AppProperties;
import kr.bora.api.socialAuth.util.CookieUtils;
import kr.bora.api.user.domain.RefreshToken;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.LoginRequestDto;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
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

    //    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final AppProperties appProperties;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

    private final MailAuthRepository mailAuthRepository;


    @Override
    public UserResponseDto signup(UserRequestDto userRequestDto) {

        User user = userRequestDto.toUserforSave(passwordEncoder);
        UserResponseDto response = UserResponseDto.of(userRepository.save(user));

        return response;
    }

    @Override
    public TokenDto login(HttpServletRequest request, HttpServletResponse response, LoginRequestDto loginRequestDto) {

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
                .build();

        refreshTokenRepository.save(refreshToken);
        tokenDto.setUserName(loginRequestDto.getUsername());

        int cookieMaxAge = (int) (THREE_DAYS_MSEC / 60);
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN); // ???????????? ????????? 3?????? ????????? ??????
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getValue(), cookieMaxAge);

        return tokenDto;
    }

    @Override
    public TokenDto reIssue(TokenRequestDto tokenRequestDto) {

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName());


        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());

        refreshTokenRepository.save(newRefreshToken);

        return tokenDto;
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsByusername(username);
    }

}