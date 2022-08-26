package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    boolean checkChangeableAuthority(long userId, int authorityCode);

    UserWithDepartmentDto departmentAssigning(AssignDepartmentDto dto);

    void modify(UserDto.UserRequest userRequest);

    UserDto.UserResponse getMyInfo();

    void deleteUserRelate(Long userId);

    void deleteUser(UserDto.UserResponse dto);

    String getTempPassword();

    void updatePassword(String tempPassword, String username);

}
