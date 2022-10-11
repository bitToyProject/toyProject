package kr.bora.api.config;

import kr.bora.api.common.listener.InactiveChunkListener;
import kr.bora.api.common.listener.InactiveJobListener;
import kr.bora.api.common.listener.InactiveStepListener;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.domain.reader.UserStatus;
import kr.bora.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Configuration
@AllArgsConstructor

public class InactiveUserJobConfig {

    private final static int CHUNK_SIZE = 5;

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job inactiveUserJob(JobBuilderFactory jobBuilderFactory, InactiveJobListener inactiveJobListener, Step inactiveJobStep) {
        return jobBuilderFactory.get("inactiveUserJob")
                .preventRestart()
                .listener(inactiveJobListener)
                .start(inactiveJobStep)
                .build();
    }
    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory, ListItemReader<User> inactiveUserReader, InactiveChunkListener inactiveChunkListener, InactiveStepListener inactiveStepListener, TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("inactiveUserStep")
                .<User, User> chunk(CHUNK_SIZE)
                .reader(inactiveUserReader)
                .processor(inactiveUserProcessor())
                .writer(inactiveUserWriter())
                .listener(inactiveChunkListener)
                .listener(inactiveStepListener)
                .taskExecutor(taskExecutor)
                .throttleLimit(2)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("Batch_Task");
    }

    @Bean
    @StepScope
    public ListItemReader<User> inactiveUserReader(@Value("#{jobParameters[nowDate]}") Date nowDate, UserRepository userRepository) {

        LocalDateTime now = LocalDateTime.ofInstant(nowDate.toInstant(), ZoneId.systemDefault());
        List<User> inactiveUsers = userRepository.findByModDateBeforeAndUserStatusEquals(now.minusYears(1), UserStatus.ACTIVE);
        return new ListItemReader<>(inactiveUsers);
    }

    private InactiveItemProcessor inactiveUserProcessor() {
        return new InactiveItemProcessor();
    }

    private JpaItemWriter<User> inactiveUserWriter() {
        JpaItemWriter<User> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}
