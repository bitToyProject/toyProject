package kr.bora.api.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoNotificationsDto {

    // 로그인 한 유저의 모든 알림
    private List<TodoNotiDto.NotiResponse> TodoNotifications;

    // 로그인한 유저가 읽지 않은 알림 수
    private Long unreadCount;

    public static TodoNotificationsDto of(List<TodoNotiDto.NotiResponse> todoNotifications, long count) {
        return TodoNotificationsDto.builder()
                .TodoNotifications(todoNotifications)
                .unreadCount(count)
                .build();
    }

}
