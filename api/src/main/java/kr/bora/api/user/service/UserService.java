package kr.bora.api.user.service;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    UserRequestDto modify(UserRequestDto userRequestDto);

   default User dtoEntity(UserRequestDto userRequestDto, PasswordEncoder passwordEncoder) {
        User entity = User.builder()
                .userId(userRequestDto.getUserId())
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .authority(Authority.ROLE_USER)
                .build();
        return entity;
    }

    default UserRequestDto entityDto(User user, PasswordEncoder passwordEncoder) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .authority(Authority.ROLE_USER)
                .build();

        return userRequestDto;
    }

}
