package kr.bora.api.user.service;



import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Errorcode;
import kr.bora.api.user.domain.RefreshToken;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.jwt.TokenProvider;
import kr.bora.api.user.repository.RefreshTokenRepository;
import kr.bora.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public CommonResponse signup(UserRequestDto userRequestDto) {
        if (userRepository.existsByusername(userRequestDto.getUsername())) {
            return CommonResponse.fail(Errorcode.ALREADY_EXIT_USER);
        }

        User user = userRequestDto.toUser(passwordEncoder);
        UserResponseDto response = UserResponseDto.of(userRepository.save(user));
        return CommonResponse.success(response);
    }

    @Transactional
    public CommonResponse login(UserRequestDto userRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = userRequestDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return CommonResponse.success(tokenDto);
    }

    @Transactional
    public CommonResponse reIssue(TokenRequestDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return CommonResponse.fail(Errorcode.UNUSABLE_REFRESH_TOKEN);
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            return CommonResponse.fail(Errorcode.UNMATCHED_USER);
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());

        refreshTokenRepository.save(newRefreshToken);

        return CommonResponse.success(tokenDto);
    }
}