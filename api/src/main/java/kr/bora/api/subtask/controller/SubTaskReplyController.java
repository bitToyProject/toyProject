package kr.bora.api.subtask.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.subtask.dto.SubTaskReplyDto;
import kr.bora.api.subtask.service.SubTaskReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/subtasks/reply")
public class SubTaskReplyController {

    private final SubTaskReplyService subTaskReplyService;

    /**
     * SubTaskReply 등록
     * @param subTaskDto
     * @param subTaskId
     * @return
     */
    @PostMapping("/save/{subTaskId}")
    public ResponseEntity<ApiResponse> save(@RequestBody SubTaskReplyDto.Request subTaskDto, @PathVariable Long subTaskId) {
        subTaskReplyService.subtaskReplySave(subTaskDto, subTaskId);
        return ResponseEntity.ok(ApiResponse.success("response save success", subTaskDto));
    }

    /**
     * SubTaskReply 목록
     * @param subTaskId
     * @return
     */
    @GetMapping("/list/{subTaskId}")
    public ResponseEntity <ApiResponse> list(@PathVariable Long subTaskId) {
        List<SubTaskReplyDto.Response> responseList = subTaskReplyService.subtaskReplyList(subTaskId);
        return ResponseEntity.ok(ApiResponse.success("response list data", responseList));
    }

    /**
     * SubTaskReply 삭제
     * @param subTaskRno
     * @return
     */
    @DeleteMapping("/remove/{subTaskRno}")
    public ResponseEntity<ApiResponse> subTaskReplyRemove(@PathVariable Long subTaskRno) {
        subTaskReplyService.subtaskReplyRemove(subTaskRno);

        return ResponseEntity.ok(ApiResponse.success("response delete data", subTaskRno + "번 SubtaskReply가 성공적으로 삭제되었습니다."));
    }
}
