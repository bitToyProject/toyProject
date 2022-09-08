package kr.bora.api.config;

import kr.bora.api.user.domain.User;
import kr.bora.api.user.domain.reader.UserStatus;
import kr.bora.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InactiveItemTasklet implements Tasklet {

    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        //reader
        Date nowDate = (Date) chunkContext.getStepContext().getJobParameters().get("nowDate");
        LocalDateTime now = LocalDateTime.ofInstant(nowDate.toInstant(), ZoneId.systemDefault());
        List<User> inactiveUsers = userRepository.findByModDateBeforeAndUserStatusEquals(now.minusYears(1), UserStatus.ACTIVE);

        //processor
        inactiveUsers = inactiveUsers.stream()
                .map(User::setUserInactive)
                .collect(Collectors.toList());

        //writer
        userRepository.saveAll(inactiveUsers);
        return RepeatStatus.FINISHED;
    }
}
