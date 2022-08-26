package kr.bora.api.user.service;

import kr.bora.api.user.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Ref;

public interface AuthService {

    UserDto.UserResponse signup(UserDto.UserRequest userRequest);

    TokenDto login(HttpServletRequest request, HttpServletResponse response, AuthDto.LoginRequest loginRequestDto);

    TokenDto reIssue(HttpServletRequest request, HttpServletResponse response, TokenDto.TokenRequest tokenRequestDto);

    void logout(String accessToken, String refreshToken);
    boolean checkUsername(String username);

}
