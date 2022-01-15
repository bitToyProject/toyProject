package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    CommonResponse<TokenDto> login(UserRequestDto userRequestDto);
    TokenDto reIssue(TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);
}
