package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoNotificationsDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface TodoNotiService {

    SseEmitter subscribe(String lastEventId);

    void send(Long userId, Todo todo, String content);

    TodoNotificationsDto findAllById();

    void readNotification(Long id) throws Exception;
}
