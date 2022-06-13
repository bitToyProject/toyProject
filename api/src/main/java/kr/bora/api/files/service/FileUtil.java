package kr.bora.api.files.service;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.domain.Files;
import kr.bora.api.files.dto.FileDto;
import kr.bora.api.files.exception.FileException;
import kr.bora.api.files.repository.FileRepository;
import kr.bora.api.files.util.MD5Generator;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUtil {

    @Value("${bora.upload.path}")
    private String uploadPath;

    private final FileRepository fileRepository;

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Transactional
    public List<FileDto> uploadFiles(List<MultipartFile> files, FileType fileType) {

        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<FileDto> attachList = new ArrayList<>();

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* 파일 개수만큼 forEach 실행 */
        for (MultipartFile file : files) {
            try {
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                final String saveName = new MD5Generator(file.getOriginalFilename()) + "." + extension;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(uploadPath, saveName);
                file.transferTo(target);


                /* 파일 정보 저장 */
                Long userId = SecurityUtil.getCurrentUserId();
                FileDto attach = FileDto.builder()
                        .originFilename(file.getOriginalFilename())
                        .filename(saveName)
                        .path(uploadPath)
                        .fileType(fileType)
                        .userId(User.builder().userId(userId).build())
                        .build();
                /* 파일 정보 추가 */
                attachList.add(attach);
            } catch (Exception e) {
                throw new FileException("[" + file.getOriginalFilename() + "] failed to save file...");
            }
        } // end of for

        return attachList;
    }

    @Transactional(readOnly = true)
    public FileDto getFile(Long fileId) {
        Files files = fileRepository.findById(fileId).get();

        return FileDto.builder()
                .fileId(fileId)
                .originFilename(files.getOriginFilename())
                .filename(files.getFilename())
                .fileType(files.getFileType())
                .path(files.getPath())
                .build();
    }

}
