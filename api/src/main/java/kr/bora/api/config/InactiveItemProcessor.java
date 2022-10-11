package kr.bora.api.config;

import kr.bora.api.user.domain.User;
import org.springframework.batch.item.ItemProcessor;

public class InactiveItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        return user.setUserInactive();
    }
}
