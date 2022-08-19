package kr.bora.api.borateamuser.repository;

import kr.bora.api.borateamuser.domain.BoraTeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoraTeamUserRepository extends JpaRepository<BoraTeamUser, Long> {

    BoraTeamUser findByUser(Long userId);

    @Query("select case when count(t) > 0 then true else false end from BoraTeamUser t where t.user.nickName =:nickName")
    boolean checkExistNickname(@Param("nickName") String nickName);
}
