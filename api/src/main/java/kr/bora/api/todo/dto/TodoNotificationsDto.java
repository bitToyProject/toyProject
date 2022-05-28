package kr.bora.api.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TodoNotificationsDto {

    // 로그인 한 유저의 모든 알림
    private List<TodoNotificationsDto> TodoNotificationsDto;

    // 로그인한 유저가 읽지 않은 알림 수
    private Long unreadCount;

    @Builder
    public TodoNotificationsDto(List<TodoNotificationsDto> TodoNotificationsDto, Long unreadCount) {
        this.TodoNotificationsDto = TodoNotificationsDto;
        this.unreadCount = unreadCount;
    }




}
