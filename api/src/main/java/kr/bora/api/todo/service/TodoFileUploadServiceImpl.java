package kr.bora.api.todo.service;

import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.todo.dto.request.TodoFileRequestDto;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoFileUploadServiceImpl implements TodoFileUploadService{

    private final TodoRepository todoRepository;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @Override
    public List<TodoFileRequestDto> saveFile(List<MultipartFile> uploadFiles, Long todoId) {
        ArrayList<TodoFileRequestDto> resultDtoList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String ofname = uploadFile.getOriginalFilename();
            int idx = ofname.lastIndexOf(".");
            String ofheader = ofname.substring(0, idx);
            String ext = ofname.substring(idx);
            String uuid = UUID.randomUUID().toString();

            StringBuilder sb = new StringBuilder();
            sb.append(uploadPath).append(File.separator).append(uuid).append("_").append(ofheader).append(ext);
            String saveName = sb.toString();
            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
                Long userId = SecurityUtil.getCurrentUserId();
                TodoFileRequestDto fileUploadDto = TodoFileRequestDto.builder()
                        .uuid(uuid)
                        .imgName(ofname)
                        .path(uploadPath)
                        .todoId(todoId)
                        .userId(TodoUserDto.builder().userId(userId).build()) //1
                        .build();
                resultDtoList.add(fileUploadDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultDtoList;
    }
}
