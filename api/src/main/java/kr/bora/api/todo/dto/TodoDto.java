package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.upload.dto.TodoFileUploadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

    private Long todoId;
    @JsonIgnore
    private TodoUserDto userId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String assignee;

    private String nickname;

    private Integer priority;

    private Integer point = 0;
    @JsonIgnore
    private String doneTime;

    private String regDate;

    @JsonIgnore
    private String modDate;

    private TodoType todoType;

    private TodoFileUploadDto fileUpload;

    @Builder.Default
    private ArrayList<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<TodoFileUploadDto> todoFileDtoList = new ArrayList<>();

    public void addTodoFileDto(TodoFileUploadDto todoFileUploadDto) {
        todoFileDtoList.add(todoFileUploadDto);
    }

    public void setTodoFileDtoList(List<TodoFileUploadDto> todoFileDtoList) {
        this.todoFileDtoList = todoFileDtoList;
    }
}
