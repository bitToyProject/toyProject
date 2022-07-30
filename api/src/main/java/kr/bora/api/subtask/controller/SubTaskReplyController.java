package kr.bora.api.subtask.controller;

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
    public ResponseEntity save(@RequestBody SubTaskReplyDto.Request subTaskDto, @PathVariable Long subTaskId) {

        return ResponseEntity.ok(subTaskReplyService.subtaskReplySave(subTaskDto, subTaskId));
    }

    /**
     * SubTaskReply 목록
     * @param subTaskId
     * @return
     */
    @GetMapping("/list/{subTaskId}")
    public ResponseEntity <List<SubTaskReplyDto.Response>> list(@PathVariable Long subTaskId) {

        return ResponseEntity.ok(subTaskReplyService.subtaskReplyList(subTaskId));
    }

    /**
     * SubTaskReply 삭제
     * @param subTaskRno
     * @return
     */
    @DeleteMapping("/remove/{subTaskRno}")
    public ResponseEntity<String> subTaskReplyRemove(@PathVariable Long subTaskRno) {
        subTaskReplyService.subtaskReplyRemove(subTaskRno);

        return ResponseEntity.ok(subTaskRno + "번 SubtaskReply가 성공적으로 삭제되었습니다.");
    }
}
