package kr.bora.api.teamuser.repository;

import kr.bora.api.teamuser.domain.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser,Long> {

    List<TeamUser> findTeamUsersByTeamId(Long teamId);

}
