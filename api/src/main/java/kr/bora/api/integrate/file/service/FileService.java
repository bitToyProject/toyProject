package kr.bora.api.integrate.file.service;

import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileService {


    List<ResFileInfo> saveFileListToLocal(String params, List<MultipartFile> files);
}
