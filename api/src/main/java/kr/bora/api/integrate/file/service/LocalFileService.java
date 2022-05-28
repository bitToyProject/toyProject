package kr.bora.api.integrate.file.service;

import java.util.List;
import kr.bora.api.integrate.file.domain.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface LocalFileService {

    Resource copyMultipartFile( MultipartFile multipartFile);
}
