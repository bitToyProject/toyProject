package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TodoRequestCommand {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class TodoRequest{
    private String title;
    private String start;
    private String end;
    private String description;
    private String viewer;
    private int priority;

    public TodoDto toDto(){
      Long userId = SecurityUtil.getCurrentUserId();
      return  TodoDto.builder()
          .userId(UserRequestDto.builder().userId(userId).build())
          .title(title)
          .start(start)
          .end(end)
          .description(description)
          .viewer(viewer)
          .priority(priority)
          .build();

    }

  }

}
