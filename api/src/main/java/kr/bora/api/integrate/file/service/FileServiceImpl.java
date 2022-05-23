package kr.bora.api.integrate.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import kr.bora.api.integrate.exception.FileRequestFileNotException;
import kr.bora.api.integrate.file.domain.entity.FileInfo;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final ObjectMapper objectMapper;
    private final LocalFileService localFileService;

    @Override
    public List<ResFileInfo> saveFileListToLocal(String params, List<MultipartFile> files) {
        if(files.isEmpty())throw new FileRequestFileNotException("File Not Exception");

        FileInfo fileInfo = null;
        try {
            objectMapper.readValue(params, FileInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        log.info(fileInfo.toString());

        try {
            List<Resource> resources = files.stream()
                .map(this.localFileService::copyMultipartFile)
                .collect(Collectors.toList());
//            localFileService.saveFilesInfo()
            return null;
//            return ResFileInfo.toResFileInfo(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
