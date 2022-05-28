package kr.bora.api.todo.dto;

import kr.bora.api.todo.domain.TodoNotification;
import kr.bora.api.user.util.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoNotiDto {

    private Long notiId;

    private TodoUserDto userId;

    //알림 내용
    private String content;

    //알림 url
    private String url;

    // 알림이 생성된 날짜
    private Integer[] createAt;

    // 알림 읽음 여부
    private boolean isRead;

    @Builder
    public TodoNotiDto(Long notiId, TodoUserDto userId, String content, String url, Integer[] createAt, boolean isRead) {
        this.notiId = notiId;
        this.userId = userId;
        this.content = content;
        this.url = url;
        this.createAt = createAt;
        this.isRead = isRead;
    }


    public static TodoNotiDto from(TodoNotification todoNotification) {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoNotiDto.builder()
                .notiId(todoNotification.getNotiId())
                .content(todoNotification.getContent())
                .url(todoNotification.getUrl())
//                .createAt(todoNotification.getRegDate())
                .isRead(todoNotification.isRead())
                .userId(TodoUserDto.builder().userId(userId).build())
                .build();
    }

}
