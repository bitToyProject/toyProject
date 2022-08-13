package kr.bora.api.borateam.repository;


import kr.bora.api.borateam.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select case when count(t) > 0 then true else false end from Team t where t.teamName =:teamName")
    boolean checkExistTeamName(@Param("teamName") String teamName);

}
