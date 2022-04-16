package kr.bora.api.upload.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.upload.domain.TodoFileUpload;
import kr.bora.api.upload.dto.TodoFileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    List<TodoFileUploadDto> saveFile(List<MultipartFile> uploadFile);

//    void fileUpload(Long fileId);

    default TodoFileUpload dtoToEntity(TodoFileUploadDto todoFileUploadDto) {
        return TodoFileUpload.builder()
                .uuid(todoFileUploadDto.getUuid())
                .originalFilename(todoFileUploadDto.getOriginalFilename())
                .saveFilename(todoFileUploadDto.getSaveFilename())
                .imageName(todoFileUploadDto.getImgName())
                .path(todoFileUploadDto.getPath())
                .todo(Todo.builder().todoId(todoFileUploadDto.getTodoId()).build())
                .user((todoFileUploadDto.getUserId()).saveId(todoFileUploadDto.getUserId()))
                .build();

    }
}
