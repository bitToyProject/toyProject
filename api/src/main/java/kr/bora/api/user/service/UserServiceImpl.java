package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Status;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TodoRepository todoRepository;

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

    public UserRequestDto modify(UserRequestDto userRequestDto) {

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        User user = repository.getById(userRequestDto.getUserId());
//
//        user.changePassword(passwordEncoder.encode(userRequestDto.getPassword()));
//
//        user.changeNickname(userRequestDto.getNickName());
//
//        user.changeLastName(userRequestDto.getLastName());
//
//        user.changeFirstName(userRequestDto.getFirstName());
//
//        user.changeGender(userRequestDto.getGender());
//
//        user.changePhoneNum(userRequestDto.getPhoneNum());

        //TODO : 위 코드 간략화
        User user = userRequestDto.toPasswordEncoding();

        repository.save(user);

        UserRequestDto dtoEntity = entityDto(user);

        return dtoEntity;
    }

    public void deleteUserRelate(UserRequestDto dto) {
        User user = dto.toUserEntity(dto);
        todoRepository.deleteTodoUserId(user.getUserId());
    }

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


}