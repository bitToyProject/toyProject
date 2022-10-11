package kr.bora.api.todo.dto;

import kr.bora.api.todo.domain.TodoNotification;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TodoNotiDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NotiRequest {
        private Long notiId;

        //알림 내용
        private String content;

        //알림 url
        private String url;

        // 알림이 생성된 날짜
        private LocalDateTime createAt;

        // 알림 읽음 여부
        private boolean isRead;

        public TodoNotification toEntity() {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoNotification.builder()
                    .receiver(User.builder().userId(userId).build())
                    .content(content)
                    .url(url)
                    .isRead(isRead)
                    .build();
        }

    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NotiResponse {
        private Long notiId;

        private Long userId;

        //알림 내용
        private String content;

        //알림 url
        private String url;

        // 알림이 생성된 날짜
        private LocalDateTime createAt;

        // 알림 읽음 여부
        private boolean isRead;

        public NotiResponse(TodoNotification todoNotification) {
            this.notiId = todoNotification.getNotiId();
            this.url = todoNotification.getUrl();
            this.isRead = todoNotification.isRead();
            this.content = todoNotification.getContent();
            this.userId = todoNotification.getReceiver().getUserId();
            this.createAt = todoNotification.getRegDate();
        }

    }




//    public static TodoNotiDto from(TodoNotification todoNotification) {
//        Long userId = SecurityUtil.getCurrentUserId();
//        return TodoNotiDto.builder()
//                .notiId(todoNotification.getNotiId())
//                .content(todoNotification.getContent())
//                .url(todoNotification.getUrl())
////                .createAt(todoNotification.getRegDate())
//                .isRead(todoNotification.isRead())
//                .userId(TodoUserDto.builder().userId(userId).build())
//                .build();
//    }

}
