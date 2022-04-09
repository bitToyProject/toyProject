package kr.bora.api.subtask.service;

import kr.bora.api.subtask.dto.SubTaskReplyDto;
import kr.bora.api.subtask.repository.SubTaskReplyRepository;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTaskReplyServiceImpl implements SubTaskReplyService{

    private final SubTaskReplyRepository subTaskReplyRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long subtaskReplySave(SubTaskReplyDto subTaskReplyDto, Long subtaskId) {
        UserResponseDto replyer = getUserNickname();
        return null;
    }

    @Override
    @Transactional
    public void subtaskReplyRemove(Long subtaskRno) {

    }

    @Override
    @Transactional(readOnly = true)
    public List<SubTaskReplyDto> subtaskReplyList(Long subtaskId) {
        return null;
    }

    private UserResponseDto getUserNickname() {
        UserResponseDto replyer = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of)
                .orElseThrow();
        return replyer;
    }
}
