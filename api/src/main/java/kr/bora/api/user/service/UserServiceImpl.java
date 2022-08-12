package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Status;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.repository.TodoNotiRepository;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.AssignDepartmentDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.dto.UserWithDepartmentDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TodoRepository todoRepository;

    private final SubTaskRepository subTaskRepository;

    private final TodoNotiRepository todoNotiRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto getMyInfo() {
        return repository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Override
    public boolean checkChangeableAuthority(long userId, int authorityCode) {
        User user = repository.findById(userId)
            .orElseThrow(() -> new InvalidParameterException("해당 id의 유저가 존재하지 않습니다."));
        return authorityCode >= user.getAuthority().getCode();
    }

    @Override
    public UserWithDepartmentDto departmentAssigning(AssignDepartmentDto dto) {
        User user = dto.toUser();
        int dup = repository.assiningUserDepartment(user);
        return UserWithDepartmentDto.toDto(user);
    }

    @Override
    @Transactional
    public UserRequestDto modify(UserRequestDto userRequestDto) {

        User user = userRequestDto.toPasswordEncoding();

        repository.save(user);

        UserRequestDto dtoEntity = entityDto(user);

        return dtoEntity;
    }
    @Override
    @Transactional
    public void deleteUserRelate(UserRequestDto dto) {
        User user = dto.toUserEntity(dto);
        todoRepository.deleteTodoUserId(user.getUserId());
        subTaskRepository.deleteSubTaskByUserId(user.getUserId());
    }

    @Override
    @Transactional
    public CommonResponse<UserResponseDto> deleteUser(UserRequestDto dto) {
        User user = dto.toUserEntity(dto);
        deleteUserRelate(dto);
        try {
            repository.deleteById(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(Status.JPACONDUCTERROR);
        }
        return CommonResponse.success();
    }

    @Override
    public String getTempPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";

        int idx = 0;

        for (int i = 0; i <10; i++) {
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }
        return pwd;
    }

    @Override
    @Transactional
    public void updatePassword(String tempPassword, String username) {

        String encodeTempPassword = passwordEncoder.encode(tempPassword);

        User user = repository.findByusername(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.changePassword(encodeTempPassword);

    }


}