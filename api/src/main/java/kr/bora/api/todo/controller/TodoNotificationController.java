package kr.bora.api.todo.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.todo.dto.TodoNotificationsDto;
import kr.bora.api.todo.service.TodoNotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todonoti")
public class TodoNotificationController {

    private final TodoNotiService todoNotiService;

    /**
     * @title 로그인 한 유저 sse 연결
//     */
//    @GetMapping(value="/subscribe", produces = "text/event-stream")
//    public SseEmitter subscribe(@RequestParam(value = "lastEventId", required = false, defaultValue = "") String lastEventId) {
//        return todoNotiService.subscribe(lastEventId);
//    }

    /**
     * @title 로그인 한 유저 sse 연결 - 테스트용
     */
    @GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
    public SseEmitter subscribe(@PathVariable Long id,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return todoNotiService.subscribe(id, lastEventId);
    }

    @GetMapping("/test")
    public ModelAndView noti() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("notification");
        return mv;
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
    public ResponseEntity<ApiResponse> readNotification(@PathVariable Long notiId) throws Exception {
        todoNotiService.readNotification(notiId);
        return ResponseEntity.ok(ApiResponse.success("Response Read Notifications", notiId + "번 Todo 알림을 읽었습니다."));
    }

}
