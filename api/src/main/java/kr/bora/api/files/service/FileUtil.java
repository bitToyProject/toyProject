package kr.bora.api.files.service;

import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.exception.ErrorCode;
import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.domain.Files;
import kr.bora.api.files.dto.FileDto;
import kr.bora.api.files.repository.FileRepository;
import kr.bora.api.files.util.MD5Generator;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
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
@Service
public class FileUtil {

    @Value("${bora.upload.path}")
    private String uploadPath;

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Transactional
    public List<FileDto> uploadFiles(List<MultipartFile> files, FileType fileType, Long todoId, Long textId) {

        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<FileDto> attachList = new ArrayList<>();
        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        if (todoId == null || textId == null) {

            /* 파일 개수만큼 forEach 실행 */
            for (MultipartFile file : files) {
                try {
                    /* 파일 확장자 */
                    final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                    /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                    final String saveName = new MD5Generator(file.getOriginalFilename()) + "_" + getRandomString() + "." + extension;

                    /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                    File target = new File(uploadPath, saveName);
                    file.transferTo(target);


                    /* 파일 정보 저장 */
                    Long userId = SecurityUtil.getCurrentUserId();
                    UserResponseDto nick = getUserNickname();
                    FileDto attach = FileDto.builder()
                            .originFilename(file.getOriginalFilename())
                            .filename(saveName)
                            .path(uploadPath)
                            .fileType(fileType)
                            .userId(User.builder().userId(userId).build().getUserId())
                            .textEditorId(textId)
                            .todoId(todoId)
                            .nickname(fileType == FileType.LOCAL ? User.builder().nickName(nick.getNickname()).build().getNickName() : null)
                            .deleteType("N")
                            .build();

                    /* 파일 정보 추가 */
                    attachList.add(attach);
                    Files filesSave = attach.toEntity();
                    fileRepository.save(filesSave);

                } catch (Exception e) {
                    throw new BoraException(ErrorCode.FAIL_SAVE_FILE, "[" + file.getOriginalFilename() + "] failed to save file...");
                }
            } // end of for}
//        }
        return attachList;
    }

    @Transactional(readOnly = true)
    public FileDto getFile(Long fileId, FileType fileType) {

        String getFileName = fileRepository.findByFileIdAndFileType(fileId, fileType);

        return FileDto.builder()
                .filename(getFileName)
                .build();
    }


    @Transactional
    public void updateFiles(List<MultipartFile> multipartFiles, FileType fileType, Long todoId, Long textId, Long fileId) {

        // todo file 수정
        List<Files> todoFileId = fileRepository.findByTodoFileId(fileType, todoId);

        for (Files todoFiles : todoFileId) {
            // 기존 파일 삭제
            fileRepository.todoFilesUpdate(todoFiles.getFileId(), todoFiles.getFileType(), todoFiles.getTodoId());
        }
        // 새로 저장
        if (multipartFiles != null && todoId != null) {
            uploadFiles(multipartFiles, fileType, todoId, null);
        }

        // 텍스트 에디터 파일 수정
        List<Files> textFileId = fileRepository.findByTextFileId(fileType, textId);

        for (Files textFiles : textFileId) {
            fileRepository.textFilesUpdate(textFiles.getFileId(), textFiles.getFileType(), textFiles.getTextId());
        }

        if (multipartFiles != null && textId != null) {
            uploadFiles(multipartFiles, fileType, null, textId);
        }

        // Local File 수정
        List<Files> localFileId = fileRepository.findByFileId(fileId);
        for (Files localFiles : localFileId) {
            fileRepository.localFilesUpdate(localFiles.getFileId(), localFiles.getFileType());
        }

        if (multipartFiles != null && fileId != null) {
            uploadFiles(multipartFiles, fileType, null, null);
        }

    }

    @Transactional
    public void localFileRemove(Long fileId) {
        String localFileUploader = fileRepository.getLocalFileUploader(fileId);

        if (localFileUploader != null) {
            fileRepository.localFilesDelete(fileId);
        } else {
            throw new IllegalArgumentException("로컬 파일 업로더만 삭제가 가능합니다.");
        }
    }

    private UserResponseDto getUserNickname() {
        UserResponseDto replyer = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of)
                .orElseThrow();
        return replyer;
    }

}
