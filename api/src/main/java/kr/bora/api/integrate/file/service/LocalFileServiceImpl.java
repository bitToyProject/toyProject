package kr.bora.api.integrate.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import kr.bora.api.common.util.ObjUtils;
import kr.bora.api.integrate.exception.FileRequestFileNotException;
import kr.bora.api.integrate.file.domain.entity.FileInfo;
import kr.bora.api.integrate.file.domain.repository.LocalFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalFileServiceImpl implements LocalFileService{

    private final LocalFileRepository localRepository;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @Override
    public Resource copyMultipartFile(MultipartFile multipartFile) {
        try{
            Path filePath = Paths.get(
                uploadPath + File.separator + StringUtils.cleanPath(
                    ObjUtils.getSafeString(multipartFile.getOriginalFilename())));
            Files.copy(multipartFile.getInputStream(), filePath);
            return new PathResource(filePath);
        }catch (FileAlreadyExistsException e){
          log.info("file already Exist");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileRequestFileNotException(
                "[LocalFileServiceImpl] : During copy file on local exception occoured");
        }
    }
}
