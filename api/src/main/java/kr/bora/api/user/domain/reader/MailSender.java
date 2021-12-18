package kr.bora.api.user.domain.reader;

import kr.bora.api.user.domain.User;

public class MailSender {
    private final User data;

    public MailSender(User data) {
        this.data = data;
    }
    public User getThisData(){
        return data;
    }
}

