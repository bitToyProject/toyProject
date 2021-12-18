package kr.bora.api.user.repository;

import kr.bora.api.user.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {
}
