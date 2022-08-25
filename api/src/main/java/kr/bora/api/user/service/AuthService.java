package kr.bora.api.user.service;

import kr.bora.api.user.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Ref;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    TokenDto login(HttpServletRequest request, HttpServletResponse response, LoginRequestDto loginRequestDto);

    TokenDto reIssue(HttpServletRequest request, HttpServletResponse response, TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);

}
