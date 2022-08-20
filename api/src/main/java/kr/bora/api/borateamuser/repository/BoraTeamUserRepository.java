package kr.bora.api.borateamuser.repository;

import kr.bora.api.borateamuser.domain.BoraTeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoraTeamUserRepository extends JpaRepository<BoraTeamUser, Long> {

//    BoraTeamUser findByUser(Long userId);

    List<BoraTeamUser> findBoraTeamUserByBoraTeamId(Long teamId);

    List<BoraTeamUser> findBoraTeamUserByUser(Long leaderId);

    @Query("select case when count(t) > 0 then true else false end from BoraTeamUser t where t.user.nickName =:nickName")
    boolean checkExistNickname(@Param("nickName") String nickName);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BoraTeamUser br where br.boraTeam.id=:teamId")
    void deleteBoraTeamUserByByTeamId(@Param("teamId") Long teamId);

}
