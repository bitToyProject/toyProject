package kr.bora.api.borateam.repository;


import kr.bora.api.borateam.domain.BoraTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoraTeamRepository extends JpaRepository<BoraTeam, Long> {

    @Query("select case when count(t) > 0 then true else false end from BoraTeam t where t.teamName =:teamName")
    boolean checkExistTeamName(@Param("teamName") String teamName);

    @Query("select bt.leaderId from BoraTeam bt where bt.id=:teamId")
    Long getLeaderId(@Param("teamId") Long teamId);

    BoraTeam findByTeamName(String name);

}
