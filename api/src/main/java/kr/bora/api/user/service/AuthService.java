package kr.bora.api.user.service;

<<<<<<< HEAD
import kr.bora.api.user.dto.LoginRequestDto;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    TokenDto login(LoginRequestDto loginRequestDto);
    TokenDto reIssue(TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);
}
