package kr.bora.api.team.repository;

import kr.bora.api.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    @Query("select case when count(t) > 0 then true else false end from Team t where t.teamName =:teamName")
    boolean checkExistTeamName(@Param("teamName") String teamName);

}
