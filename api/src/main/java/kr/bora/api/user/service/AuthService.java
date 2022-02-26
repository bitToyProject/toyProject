package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.dto.*;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    CommonResponse<TokenDto> login(LoginRequestDto loginRequestDto);
    TokenDto reIssue(TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);
}
