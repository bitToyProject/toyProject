package kr.bora.api.mailauth.service;

import kr.bora.api.mailauth.domain.dto.AuthMailDto;

public interface MailSendService {
    void sendAuthMail(AuthMailDto dto);

    boolean checkMailAuthKey(String username, String authKey);

    boolean isCheckedAuthMail(String username);
}
