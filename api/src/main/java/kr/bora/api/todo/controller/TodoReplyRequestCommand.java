package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TodoReplyRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoReplyRequest{
        private String text;
        private String todoReplyer;
        private TodoDto todo;

        public TodoReplyDto toDto(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoReplyDto.builder()
                    .userId(TodoUserDto.builder().userId(userId).build())
                    .todoId(todoId)
                    .text(text)
                    .todoReplyer(todoReplyer)
                    .build();
        }
    }
}