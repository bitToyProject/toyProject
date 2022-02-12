package kr.bora.api.userteam.repository;

import kr.bora.api.userteam.domain.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam,Long> {

}
