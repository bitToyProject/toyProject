package kr.bora.api.files.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.dto.FileDto;
import kr.bora.api.files.service.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FilesController {

    private final FileUtil fileUtil;

    @Value("${bora.upload.path}")
    private String uploadPath;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> filesSave(@RequestPart(value = "files") List<MultipartFile> multipartFiles) {

        return ResponseEntity.ok(ApiResponse.success("LOCAL 파일 등록 성공", fileUtil.uploadFiles(multipartFiles, FileType.LOCAL, null, null)));

    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId, @RequestParam FileType fileType) throws IOException {
        FileDto fileDto = fileUtil.getFile(fileId, fileType);

        Path path = Paths.get(fileDto.getPath() + "/" + fileDto.getFilename());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        // 다운로드 파일 명 한글 깨짐 처리
                        + new String(fileDto.getOriginFilename().getBytes("UTF-8"), "ISO-8859-1") + "\"")
                .body(resource);
    }

    @GetMapping("/display/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileId") Long fileId, @RequestParam FileType fileType) {

        ResponseEntity<byte[]> result = null;

        FileDto fileDto = fileUtil.getFile(fileId, fileType);

        String filename = fileDto.getFilename();

        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);

        String[] ext = {"jpg", "jpeg", "png", "gif", "bmp"};

        try {
            String srcFileName = URLDecoder.decode(filename, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", String.valueOf(MediaType.IMAGE_JPEG));

            if (Arrays.stream(ext).anyMatch(e -> e.contains(extensionName))) { // 이미지 확장자 중 단 하나라도 extensionname의 값을 포함하고 있다면 이미지를 보여줌
                result = ResponseEntity.ok().headers(headers).body(FileCopyUtils.copyToByteArray(file));
            } else {
                throw new RuntimeException("이미지 확장자가 아닙니다. " + extensionName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    @PutMapping("/update/{fileId}")
    public ResponseEntity<ApiResponse> updateFileList(@RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles,
                                                      @PathVariable Long fileId) {

        fileUtil.updateFiles(multipartFiles, FileType.LOCAL, null, null, fileId);


        return ResponseEntity.ok(ApiResponse.success("성공", fileId + "번 파일이 수정되었습니다. " + FileType.LOCAL));
    }
//

    /**
     * Local 파일 완전 삭제
     *
     * @param fileId
     * @return
     */
    @DeleteMapping("/remove/{fileId}")
    public ResponseEntity<ApiResponse> removeFileList(@PathVariable("fileId") Long fileId,
                                                      @RequestParam(defaultValue = "LOCAL") FileType fileType) {

        fileUtil.localFileRemove(fileId);

        return ResponseEntity.ok(ApiResponse.success("response delete success", FileType.LOCAL + "파일이 성공적으로 삭제 되었습니다."));
    }


}
