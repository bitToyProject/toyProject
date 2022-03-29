package kr.bora.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "slack")
public class SlackConfig {
    private String token;
    private String server;
    private String client;

    public String getToken() {
        return token;
    }

    public String getServer() {
        return server;
    }

    public String getClient() {
        return client;
    }
}
