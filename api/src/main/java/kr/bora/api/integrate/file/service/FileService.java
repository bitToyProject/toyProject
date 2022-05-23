package kr.bora.api.integrate.file.service;

import java.util.List;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {


    List<ResFileInfo> saveFileListToLocal(String params, List<MultipartFile> files);
}
