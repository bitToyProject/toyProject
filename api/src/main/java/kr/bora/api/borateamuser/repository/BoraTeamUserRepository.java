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

    List<BoraTeamUser> findBoraTeamUserByBoraTeamTeamName(String teamName);

    @Query("select count(t) from BoraTeamUser t where t.user.nickName =:nickName and t.teamName=:teamName")
    Long checkExistNickname(@Param("nickName") String nickName, @Param("teamName") String teamName);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BoraTeamUser br where br.boraTeam.id=:teamId")
    void deleteBoraTeamUserByByTeamId(@Param("teamId") Long teamId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM BoraTeamUser br where br.teamMembers=:nickName and br.teamName=:teamName")
    void deleteBoraTeamUser(@Param("nickName") String nickName, @Param("teamName") String teamName);


}
