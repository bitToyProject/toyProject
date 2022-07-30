package kr.bora.api.user.service;

import kr.bora.api.user.dto.LoginRequestDto;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    TokenDto login(HttpServletRequest request, HttpServletResponse response, LoginRequestDto loginRequestDto);

    TokenDto reIssue(TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);
}
