package kr.bora.api.subtask.dto;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubTaskReply;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SubTaskReplyDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long subtaskReplyId;

        private User user;

        private Long subtaskId;

        private String text;

        private String subtaskReplyer;

        private String regDate;

        private String modDate;

        // 댓글 작성자는 회원의 닉네임으로 한다.
        public String setSubtaskReplyer(String subtaskReplyer) {
            this.subtaskReplyer = subtaskReplyer;
            return subtaskReplyer;
        }

        /* Dto -> Entity */
        public SubTaskReply toEntity(Long subtaskId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return SubTaskReply.builder()
                    .subTask(SubTask.builder().subTaskId(subtaskId).build())
                    .user(User.builder().userId(userId).build())
                    .text(text)
                    .subtaskReplyer(subtaskReplyer)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long subtaskReplyId;

        private Long userId;

        private Long subtaskId;

        private String text;

        private String subtaskReplyer;

        private String regDate;

        private String modDate;

        /* Entity -> Dto */
        public Response(SubTaskReply subTaskReply) {
            this.subtaskReplyId = subTaskReply.getSubtaskReplyId();
            this.subtaskId = subTaskReply.getSubTask().getSubTaskId();
            this.userId = subTaskReply.getUser().getUserId();
            this.text = subTaskReply.getText();
            this.regDate = subTaskReply.getRegDate();
            this.modDate = subTaskReply.getModDate();
            this.subtaskReplyer = subTaskReply.getSubtaskReplyer();
        }
    }

}
