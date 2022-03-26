package kr.bora.api.todo.dto;

import kr.bora.api.user.domain.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@NoArgsConstructor
@Log4j2
@Builder
@AllArgsConstructor
public class TodoUserDto {

    private Long userId;

    private String username;

    private String nickname;

    public User saveId(TodoUserDto dto) {

        return User.builder().userId(dto.userId).build();
    }

    public User saveNickname(TodoUserDto dto) {

        return User.builder().nickName(dto.nickname).build();
    }


}
