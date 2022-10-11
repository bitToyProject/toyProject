package kr.bora.api;

import kr.bora.api.socialAuth.properties.AppProperties;
import kr.bora.api.socialAuth.properties.CorsProperties;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties({AppProperties.class, CorsProperties.class})
@EnableJpaRepositories
@SpringBootApplication
@EnableBatchProcessing
@EnableJpaAuditing
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
