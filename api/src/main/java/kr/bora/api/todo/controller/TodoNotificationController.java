package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.TodoNotificationsDto;
import kr.bora.api.todo.service.TodoNotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class TodoNotificationController {

    private final TodoNotiService todoNotiService;

    /**
     * @title 로그인 한 유저 sse 연결
     */
    @GetMapping(value="/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe(@RequestParam(value = "lastEventId", required = false, defaultValue = "") String lastEventId) {
        return todoNotiService.subscribe(lastEventId);
    }

    /**
     * @title 로그인 한 유저의 모든 알람 조회
     */
    @GetMapping("/notifications")
    public ResponseEntity<TodoNotificationsDto> notifications() {
        return ResponseEntity.ok().body(todoNotiService.findAllById());
    }

    /**
     * @title 알림 읽음 상태 변경
     */

    @PatchMapping("/notifications/{notiId}")
    public ResponseEntity<Void> readNotification(@PathVariable Long notiId) throws Exception {
        todoNotiService.readNotification(notiId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
