package kr.bora.api.integrate.file.controller;

import java.util.List;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import kr.bora.api.integrate.file.service.FileService;
import kr.bora.api.integrate.file.service.LocalFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/ListVersion")
    public List<ResFileInfo> getAndSaveFileList(
        @RequestParam(name = "params")String params,
        @RequestPart(value = "files") List<MultipartFile> files) {
        List<ResFileInfo> result = fileService.saveFileListToLocal(params, files);
        return result;
    }

}
