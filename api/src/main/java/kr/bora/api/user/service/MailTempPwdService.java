package kr.bora.api.user.service;

import kr.bora.api.user.dto.UserDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailTempPwdService {
    UserDto.MailTempPwdDto createMail(String username, String tempPwd);

    void sendMail(UserDto.MailTempPwdDto mailTempPasswordDto) throws MessagingException, UnsupportedEncodingException;
}
