package kr.bora.api.user.service;

import kr.bora.api.mailauth.MailUtil;
import kr.bora.api.user.dto.MailTempPasswordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailTempPwdServiceImpl implements MailTempPwdService {

    private final JavaMailSender mailSender;

    private static final String title = "BORA 임시 비밀번호 발급 안내";

    private static final String message = "안녕하세요. Bora 임시 비밀번호 안내입니다. "
            + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n";

    private static final String fromAddress = "noreply.bora@gmail.com";


    @Override
    public MailTempPasswordDto createMail(String tempPassword, String username) {

        return MailTempPasswordDto.builder()
                .toAddress(username)
                .title(title)
                .message(tempPassword)
                .fromAddress(fromAddress)
                .build();
    }

    @Override
    public void sendMail(MailTempPasswordDto mailTempPasswordDto) throws MessagingException, UnsupportedEncodingException {

        MailUtil mailMessage = new MailUtil(mailSender);
        StringBuffer st = new StringBuffer();
        mailMessage.setTo(mailTempPasswordDto.getToAddress());
        mailMessage.setSubject(mailTempPasswordDto.getTitle());
        mailMessage.setText(st.append("<h1>[BORA] 임시 비밀번호 발급</h1>")
                .append("안녕하세요 Bora 임시비밀번호 안내입니다."+"</br>")
                .append("회원님의 임시 비밀번호는 아래와 같습니다."+"</br>")
                .append(mailTempPasswordDto.getMessage())
                .toString());
        mailMessage.setFrom(mailTempPasswordDto.getFromAddress(), "BORA");

        mailMessage.send();
    }
}
