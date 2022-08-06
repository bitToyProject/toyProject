package kr.bora.api.todo.dto.request;

import kr.bora.api.todo.dto.TodoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoLikeRequestDto {

    private TodoDto todo;

//    public TodoLikeDto toLikeDto(Long todoId) {
//        Long userId = SecurityUtil.getCurrentUserId();
//        return TodoLikeDto.builder()
//                .userId(TodoUserDto.builder().userId(userId).build())
//                .todoId(todoId)
//                .build();
//    }
}
