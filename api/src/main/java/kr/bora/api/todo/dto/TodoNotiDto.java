package kr.bora.api.todo.dto;

import kr.bora.api.user.util.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoNotiDto {

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
    public TodoNotiDto(TodoUserDto userId, String content, String url, Integer[] createAt, boolean isRead) {
        this.userId = userId;
        this.content = content;
        this.url = url;
        this.createAt = createAt;
        this.isRead = isRead;
    }


    public TodoNotiDto toDto() {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoNotiDto.builder()
                .userId(TodoUserDto.builder().userId(userId).build())
                .content(content)
                .url(url)
                .createAt(createAt)
                .isRead(isRead)
                .build();
    }


}
