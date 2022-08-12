package kr.bora.api.user.service;

import kr.bora.api.user.dto.MailTempPasswordDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailTempPwdService {
    MailTempPasswordDto createMail(String tempPassword, String username);

    void sendMail(MailTempPasswordDto mailTempPasswordDto) throws MessagingException, UnsupportedEncodingException;
}
