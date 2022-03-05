package kr.bora.api.user.service;

<<<<<<< HEAD
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    TokenDto login(UserRequestDto userRequestDto);
=======
import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.dto.*;

public interface AuthService {
    UserResponseDto signup(UserRequestDto userRequestDto);
    CommonResponse<TokenDto> login(LoginRequestDto loginRequestDto);
>>>>>>> df61ecb844f5d5873c818a6dd220d3a55f823a29
    TokenDto reIssue(TokenRequestDto tokenRequestDto);
    boolean checkUsername(String username);
}
