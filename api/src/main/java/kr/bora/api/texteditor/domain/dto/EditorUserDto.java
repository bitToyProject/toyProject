package kr.bora.api.texteditor.domain.dto;

import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditorUserDto {

    private Long userId;

    public User saveId(EditorUserDto dto) {
        return User.builder().userId(dto.userId).build();
    }
}
