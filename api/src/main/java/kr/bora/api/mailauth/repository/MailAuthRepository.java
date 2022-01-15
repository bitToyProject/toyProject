package kr.bora.api.mailauth.repository;

import kr.bora.api.mailauth.domain.entity.AuthMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MailAuthRepository extends JpaRepository<AuthMail,Long> {
    @Query(value = "SELECT a.authMailKey FROM AuthMail a WHERE a.authMail =:authmail")
    String findAuthMailKeyByAuthMail(@Param("authmail") String authmail);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update AuthMail a set a.authStatus ='CHECKED' where a.authMail=:authmail")
    void updateMailCheckStatus(@Param("authmail")String authmail);
}
