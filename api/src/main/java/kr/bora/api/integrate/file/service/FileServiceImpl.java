package kr.bora.api.integrate.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bora.api.integrate.exception.FileRequestFileNotException;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import kr.bora.api.integrate.file.domain.entity.FileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final ObjectMapper objectMapper;
    private final LocalFileService localFileService;

    @Override
    public List<ResFileInfo> saveFileListToLocal(String params, List<MultipartFile> files) {
        if(files.isEmpty())throw new FileRequestFileNotException("File Not Exception");

        FileInfo fileInfo = null;
        //파일에 대한 정보가 담긴 params의 값들을 key : value 형식으로 읽어들인다.
        try {
            objectMapper.readValue(params, FileInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        log.info(fileInfo.toString());
        //TODO : ObjectMapper가 리스트 형식의 JSON 정보를 읽어들이고 그것이 리스트가 되는지 하나의 JSON형식의 객체가 되는지 확인 필요
        try {
            List<Resource> resources = files.stream()
                .map(this.localFileService::copyMultipartFile)
                .collect(Collectors.toList());
            return null;
//            return ResFileInfo.toResFileInfo(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
