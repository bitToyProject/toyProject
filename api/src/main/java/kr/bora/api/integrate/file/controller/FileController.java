package kr.bora.api.integrate.file.controller;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import kr.bora.api.integrate.file.domain.dto.ResFileInfo;
import kr.bora.api.integrate.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
//@Api(tags = {"단일, 복수 파일"})
public class FileController {

    private final FileService fileService;

//    @ApiOperation(value = "복수/단일 파일 업로드",notes = "params에 String이지만 json형태로 파일에 대한 정보(어디에서 저장하는 것인지, 저장을 실행하는 유저 아이디를 받아야 한다.")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Content-type",defaultValue = MediaType.MULTIPART_FORM_DATA_VALUE+"."+MediaType.MULTIPART_MIXED_VALUE)})
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "업로드 성공"),
//        @ApiResponse(code = 500,message = "업로드 실패 리턴")
//    })
    @PostMapping("/ListVersion")
    public List<ResFileInfo> getAndSaveFileList(
        @RequestParam(name = "params")String params,
        @RequestPart(value = "files") List<MultipartFile> files) {
        List<ResFileInfo> result = fileService.saveFileListToLocal(params, files);
        return result;
    }

}
