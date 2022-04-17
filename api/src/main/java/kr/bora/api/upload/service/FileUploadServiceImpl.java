package kr.bora.api.upload.service;

import kr.bora.api.upload.dto.TodoFileUploadDto;
import kr.bora.api.upload.repository.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @Override
    @Transactional
    public ArrayList<TodoFileUploadDto> saveFile(List<MultipartFile> uploadFiles) {
        ArrayList<TodoFileUploadDto> resultFileList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String ofName = uploadFile.getOriginalFilename();
            int idx = ofName.lastIndexOf('.');
            String ofheader = ofName.substring(0, idx);
            String ext = ofName.substring(idx);
            String uuid = UUID.randomUUID().toString();

            StringBuilder sb = new StringBuilder();
            sb.append(uploadPath).append(File.separator).append(uuid).append("__").append(ofheader).append(ext);
            String saveName = sb.toString();
            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
                TodoFileUploadDto todoFileUploadDto = TodoFileUploadDto.builder()
                        .uuid(uuid)
                        .originalFilename(ofName)
                        .build();
                resultFileList.add(todoFileUploadDto);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return resultFileList;
    }

    @Override
    public List<TodoFileUploadDto> updateFile(List<MultipartFile> updateFile) {
        return null;
    }

    @Override
    public void deleteFile(Long fileId) {
        fileUploadRepository.deleteByTodoId(fileId);
    }
}
