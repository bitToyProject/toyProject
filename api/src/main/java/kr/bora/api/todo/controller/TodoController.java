package kr.bora.api.todo.controller;

//import kr.bora.api.common.response.CommonResponse;
//import kr.bora.api.todo.domain.Todo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.todo.service.TodoService;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"2. Todo"})
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    /**
     * Todo 목록
     *
     * @param pageRequestDto
     * @return
     */
    @ApiOperation(value = "Todo 리스트", notes = "Todo 리스트를 보여줍니다.")
    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<TodoDto, Object[]>> todoList(PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(service.todoList(pageRequestDto));
    }


    /**
     * todo 등록 (파일 업로드 포함)
     *
     * @param
     * @param todoDto
     * @return
     */
    @ApiOperation(value = "Todo 등록", notes = "Todo 리스트를 등록합니다.")
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> todoSave(@Valid @RequestPart(value = "file", required = false) List<MultipartFile> files,
                                                @RequestPart("todoDto") TodoDto.Request todoDto,
                                                @RequestParam(required = false) String teamName) {
//        String s = SecurityUtil.currentUserName();
//        System.out.println("2asff22 == "+s);
        service.todoSave(todoDto, files, teamName);


        return ResponseEntity.ok(ApiResponse.success("response save data", todoDto));
    }


    /**
     * Todo 상세 읽기
     *
     * @param todoId
     * @return
     */
    @ApiOperation(value = "Todo 확인", notes = "Todo를 확인합니다.")
    @GetMapping("/read/{todoId}")
    public ResponseEntity<ApiResponse> todoRead(@ApiParam(value = "Todo 번호", required = true) @PathVariable("todoId") Long todoId) {
        TodoDto.Response response = service.todoRead(todoId);
        return ResponseEntity.ok(ApiResponse.success("response read data", response));
    }

    /**
     * Todo 변경
     *
     * @param todoId
     * @param todoDto
     * @return
     */
    @ApiOperation(value = "Todo 변경", notes = " Todo를 변경합니다.")
    @PutMapping("/modify/{todoId}")
    public ResponseEntity<ApiResponse> todoModify(@ApiParam(value = "Todo 번호", required = true) @PathVariable("todoId") Long todoId,
                                                  @Valid @RequestPart("todoDto") TodoDto.Request todoDto,
                                                  @RequestPart(value = "file", required = false) List<MultipartFile> files,
                                                  @RequestParam(required = false) String teamName) {

        service.todoModify(todoId, todoDto, files, teamName);

        return ResponseEntity.ok(ApiResponse.success("response update data", todoDto));
    }

    /**
     * Todo 삭제
     *
     * @param todoId
     * @return
     */
    @ApiOperation(value = "Todo 삭제", notes = "Todo를 삭제합니다.")
    @DeleteMapping("/remove/{todoId}")
    public ResponseEntity<ApiResponse> todoRemove(@ApiParam(value = "Todo 번호", required = true) @PathVariable("todoId") Long todoId) {

        service.todoRemove(todoId);

        return ResponseEntity.ok(ApiResponse.success("response delete success", todoId + "번 Todo가 삭제되었습니다."));
    }

    @GetMapping("/assignee/noti/{todoId}")
    public ResponseEntity<List<String>> noti( @PathVariable("todoId") Long todoId) {
        // 해당 toto 글에서 협력자이므로 pathvariable 준비
        Long currentUserId = SecurityUtil.getCurrentUserId();

        List<String> assignee = service.findAssignee(currentUserId);

        return ResponseEntity.ok(assignee);
    }
}
