package kr.bora.api.mailauth;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAuthRepository extends JpaRepository<AuthMail,Long> {
    @Query(value = "SELECT a.authMailKey FROM AuthMail a WHERE a.authMail = :authmail")
    String findAuthMailKeyByAuthMail(String authmail);
}
