package kr.bora.api.todo.dto;

import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoUserDto {

    private Long userId;

    public User saveId(TodoUserDto dto) {

        return User.builder().userId(dto.userId).build();
    }



}
