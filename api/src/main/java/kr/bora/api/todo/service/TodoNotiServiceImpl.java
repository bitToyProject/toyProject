package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoNotification;
import kr.bora.api.todo.dto.TodoNotiDto;
import kr.bora.api.todo.dto.TodoNotificationsDto;
import kr.bora.api.todo.repository.EmitterRepository;
import kr.bora.api.todo.repository.TodoNotiRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoNotiServiceImpl implements TodoNotiService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;

    private final TodoNotiRepository todoNotiRepository;

    @Override
    public SseEmitter subscribe(String lastEventId) {
        Long userId = SecurityUtil.getCurrentUserId();
        String id = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        // 503 에러 방지 -> 더미 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [userId=" + userId + "]");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }
        return emitter;

    }

    public void sendToClient(SseEmitter emitter, String id, Object data) {

        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data)
            );
        } catch (IOException e) {
            emitterRepository.deleteById(id);
            log.error("SSE 연결 오류", e);
        }
    }

    @Override
    @Transactional
    public void send(Long userId, Todo todo, String content) {
        TodoNotification todoNotification = createTodoNotification(userId, todo, content);
        String id = String.valueOf(userId);
        todoNotiRepository.save(todoNotification);
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllStartWithById(id);
        sseEmitters.forEach((key, emitter) -> {
            emitterRepository.saveEventCache(key, todoNotification);
            sendToClient(emitter, key, TodoNotiDto.from(todoNotification));
        });
    }

    private TodoNotification createTodoNotification(Long userId, Todo todo, String content) {
        return TodoNotification.builder()
                .receiver(User.builder().userId(userId).build())
                .content(content)
                .todo(todo)
                .url("/todos/read/" + todo.getTodoId())
                .isRead(false)
                .build();
    }
    @Override
    @Transactional
    public TodoNotificationsDto findAllById() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<TodoNotiDto> responses = todoNotiRepository.findAllByNotiId(userId).stream()
                .map(TodoNotiDto::from)
                .collect(Collectors.toList());

        long unreadCount = responses.stream()
                .filter(notification -> !notification.isRead())
                .count();

        return TodoNotificationsDto.of(responses, unreadCount);
    }

    @Override
    @Transactional
    public void readNotification(Long id) throws Exception {
        TodoNotification todoNotification = todoNotiRepository.findById(id)
                .orElseThrow(() -> new Exception("존재하지 않는 알림입니다."));

        todoNotification.read();

    }
}
