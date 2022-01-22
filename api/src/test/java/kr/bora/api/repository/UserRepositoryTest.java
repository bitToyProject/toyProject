package kr.bora.api.repository;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertUser() {
        IntStream.rangeClosed(1, 100).forEach(i->{
            User user = User.builder()
                    .username("user" + i + "@naver.com")
                    .password(passwordEncoder.encode("woals1212!"))
                    .lastName("park"+i)
                    .firstName("jaemin"+i)
                    .gender(1)
                    .nickName("nick"+i)
                    .phoneNum("0506560"+i)
                    .authority(Authority.ROLE_USER)
                    .build();
            userRepository.save(user);
        });
    }
}
