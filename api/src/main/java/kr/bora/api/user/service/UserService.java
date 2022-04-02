package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.AssignDepartmentDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.dto.UserWithDepartmentDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    boolean checkChangeableAuthority(long userId, int authorityCode);

    UserWithDepartmentDto departmentAssigning(AssignDepartmentDto dto);

    UserRequestDto modify(UserRequestDto userRequestDto);

    public UserResponseDto getMyInfo();

    void deleteUserRelate(UserRequestDto dto);

    CommonResponse<UserResponseDto> deleteUser(UserRequestDto dto);

   default User dtoEntity(UserRequestDto userRequestDto, PasswordEncoder passwordEncoder) {
        User entity = User.builder()
                .userId(userRequestDto.getUserId())
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .nickName(userRequestDto.getNickName())
                .authority(Authority.ROLE_USER)
                .build();
        return entity;
    }

    default UserRequestDto entityDto(User user) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authority(Authority.ROLE_USER)
                .nickName(user.getNickName())
                .build();

        return userRequestDto;
    }

}
