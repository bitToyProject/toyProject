package kr.bora.api.repository;

import kr.bora.api.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class SubTaskRepository {

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Test
    public void insertSubTask() {
        IntStream.rangeClosed(1,30).forEach(i->{
            
        })
    }
}
