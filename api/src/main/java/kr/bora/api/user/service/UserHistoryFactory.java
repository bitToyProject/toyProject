package kr.bora.api.user.service;

import kr.bora.api.user.domain.User;

public interface UserHistoryFactory {
    void storeHistory(User user);
}
