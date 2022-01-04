package kr.bora.api.user.service;

import kr.bora.api.user.domain.User;
import kr.bora.api.user.domain.UserHistory;
import kr.bora.api.user.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHistoryFactoryImpl implements UserHistoryFactory {
    private final UserHistoryRepository userHistory;

    @Override
    public void storeHistory(User user) {
        UserHistory history = UserHistory.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .gender(user.getGender())
                .nickName(user.getNickName())
                .phoneNum(user.getPhoneNum())
                .authority(user.getAuthority())
                .build();
        userHistory.save(history);
    }
}
