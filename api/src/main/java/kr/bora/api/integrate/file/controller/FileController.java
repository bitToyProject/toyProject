package kr.bora.api.integrate.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import kr.bora.api.integrate.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "파일 공통 컨트롤러",tags = "단일, 복수 파일")
public class FileController {

    private final FileService fileService;
    @ApiOperation(value = "복수/단일 파일 업로드",notes = "params에 String이지만 json형태로 파일에 대한 정보(어디에서 저장하는 것인지, 저장을 실행하는 유저 아이디를 받아야 한다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "Content-type",defaultValue = MediaType.MULTIPART_FORM_DATA_VALUE,+"."+MediaType.MULTIPART_MIXED_VALUE)})
    @ApiResponses({
        @ApiResponse(code = 200, message = "업로드 성공"),
        @ApiResponse(code = 500,message = "업로드 실패 리턴")
    })
    @PostMapping("/ListVersion")
    public List<ResFileInfo> getAndSaveFileList(
        @RequestParam(name = "params")String params,
        @RequestPart(value = "files") List<MultipartFile> files) {
        List<ResFileInfo> result = fileService.saveFileListToLocal(params, files);
        return result;
    }

}
