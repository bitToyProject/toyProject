package kr.bora.api.mailauth.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

import kr.bora.api.mailauth.MailUtil;
import kr.bora.api.mailauth.domain.dto.AuthMailDto;
import kr.bora.api.mailauth.domain.entity.AuthMail;
import kr.bora.api.mailauth.repository.MailAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

    private final MailAuthRepository repository;
    private final JavaMailSender mailSender;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendAuthMail(AuthMailDto authMailDto) {

        try {
            MailUtil sendMail = new MailUtil(mailSender);
            sendMail.setSubject("[BORA] 회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[BORA 이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://localhost:8080/mail/check?username=")
                    .append(authMailDto.getAuthMail())
                    .append("&authKey=")
                    .append(authMailDto.getKey())
                    .append("'target='_blank'>이메일 인증 확인</a>")
                            .append('l')
                    .toString());
            sendMail.setFrom("noreply.bora@gmail.com", "BORA");
            sendMail.setTo(authMailDto.getAuthMail());
            sendMail.send();
        } catch (MessagingException | UnsupportedEncodingException | MailException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        AuthMail authMail = authMailDto.toAuthMail(authMailDto.getAuthMail(), authMailDto.getKey());
        repository.save(authMail);
    }

    @Override
    public boolean checkMailAuthKey(String username, String authKey) {

        if (authKey.equals(repository.findAuthMailKeyByAuthMail(username))) {
            repository.updateMailCheckStatus(username);
            return true;
        }
        return false;
    }

    @Override
    public boolean isCheckedAuthMail(String username) {
        AuthMail authMail = repository.findByAuthMailEquals(username);
        if (authMail.getAuthStatus().equals(AuthMail.AuthStatus.CHECKED)) {
            return true;
        }
        return false;
    }

}
