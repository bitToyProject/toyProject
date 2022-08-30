package kr.bora.api.repository;

import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.domain.Avatar;
import kr.bora.api.user.domain.Title;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertUser() {
        IntStream.rangeClosed(1, 3).forEach(i->{
            User user = User.builder()
                    .username("user" + i + "@naver.com")
                    .password(passwordEncoder.encode("woals1212!"))
                    .nickName("nick"+i)
                    .phoneNum("0506560"+i)
                    .authority(Authority.ROLE_USER)
                    .title(Title.BEGINNER)
                    .avatar(Avatar.DEFAULTMAN)
                    .build();

            userRepository.save(user);
        });
    }

}
