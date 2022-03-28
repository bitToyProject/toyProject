package kr.bora.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
