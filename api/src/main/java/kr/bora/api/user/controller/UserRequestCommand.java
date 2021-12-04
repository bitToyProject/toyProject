package kr.bora.api.user.controller;

import java.security.InvalidParameterException;
import javax.validation.constraints.NotNull;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestCommand {

  @Getter
  @NoArgsConstructor
  public static class RequestPasswordChanger{
    @NotNull(message = "새로운 password값이 필수 있니다.")
    private String password;
    @NotNull(message = "새로운 passwordCheck값이 필수 입니다.")
    private String checkPassword;

    public UserRequestDto toDto(){
      if(!password.equals(checkPassword)){
        throw new InvalidParameterException("새로운 비밀번호와 비밀번호 확인 값이 같지 않습니다.");
      }
      var userId = SecurityUtil.getCurrentUserId();
      return UserRequestDto.builder()
          .userId(userId)
          .password(password)
          .build();
    }
  }
  @Getter
  @NoArgsConstructor
  public static class RequestUserId{
    public static UserRequestDto toDto(){
      var userId = SecurityUtil.getCurrentUserId();
      return UserRequestDto.builder()
          .userId(userId)
          .build();
    }
  }

}
