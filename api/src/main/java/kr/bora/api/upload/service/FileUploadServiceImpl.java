package kr.bora.api.upload.service;

import kr.bora.api.upload.dto.FileUploadDto;
import kr.bora.api.upload.repository.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public ArrayList<FileUploadDto> saveFile(List<MultipartFile> uploadFiles) {
        ArrayList<FileUploadDto> resultDtoList = new ArrayList<>();
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

                FileUploadDto fileUploadDto = FileUploadDto.builder()
                        .uuid(uuid)
                        .imgName(ofname)
                        .build();
                resultDtoList.add(fileUploadDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultDtoList;
    }
}
