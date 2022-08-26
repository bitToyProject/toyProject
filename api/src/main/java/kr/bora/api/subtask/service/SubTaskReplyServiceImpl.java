package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubTaskReply;
import kr.bora.api.subtask.dto.SubTaskReplyDto;
import kr.bora.api.subtask.repository.SubTaskReplyRepository;
import kr.bora.api.user.dto.UserDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubTaskReplyServiceImpl implements SubTaskReplyService {

    private final SubTaskReplyRepository subTaskReplyRepository;

    private final UserRepository userRepository;

    /**
     * SubTaskReply 등록 비즈니스 로직
     *
     * @param subTaskReplyDto
     * @param subtaskId
     * @return
     */
    @Override
    @Transactional
    public Long subtaskReplySave(SubTaskReplyDto.Request subTaskReplyDto, Long subtaskId) {
        UserDto.UserResponse replyer = getUserNickname();
        subTaskReplyDto.setSubtaskReplyer(replyer.getNickname());

        SubTaskReply subTaskReply = subTaskReplyDto.toEntity(subtaskId);
        subTaskReplyRepository.save(subTaskReply);

        return subTaskReply.getSubtaskReplyId();
    }


    /**
     * SubTaskReply 목록 비즈니스 로직
     *
     * @param subtaskId
     * @return
     */
    @Override
    public List<SubTaskReplyDto.Response> subtaskReplyList(Long subtaskId) {

        List<SubTaskReply> subTaskReplies = subTaskReplyRepository
                .getSubTaskRepliesBySubTaskOrderByRegDate(SubTask.builder().subTaskId(subtaskId).build());

        return subTaskReplies.stream().map(SubTaskReplyDto.Response::new).collect(Collectors.toList());
    }


    /**
     * SubTaskReply 삭제 비즈니스 로직 - 삭제는 댓글 작성자만 가능 하게 하기
     *
     * @param subtaskRno
     */
    @Override
    @Transactional
    public void subtaskReplyRemove(Long subtaskRno) {
        Long loginUserId = SecurityUtil.getCurrentUserId();

        Long subTaskReplyerId = subTaskReplyRepository.getSubTaskReplyer(subtaskRno);

        // null처리 필요

        if (loginUserId == subTaskReplyerId) {
            subTaskReplyRepository.deleteById(subtaskRno);
        } else {
            throw new IllegalArgumentException("댓글 작성자 본인만 삭제가 가능합니다.");
        }

    }

    private UserDto.UserResponse getUserNickname() {
        UserDto.UserResponse replyer = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserDto.UserResponse::new)
                .orElseThrow();
        return replyer;
    }
}
