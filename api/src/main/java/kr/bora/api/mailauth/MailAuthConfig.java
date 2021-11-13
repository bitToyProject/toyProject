package kr.bora.api.mailauth;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailAuthConfig {
  @Bean
  public JavaMailSender getJavaMailSender(){
    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.starttls.required", true);
    props.put("mail.debug", true);
    props.put("mail.socket.timeout",30000);

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("wkdgothf321@gmail.com");
    mailSender.setPassword("haesol21");
    mailSender.setDefaultEncoding("utf-8");
    mailSender.setJavaMailProperties(props);

    return mailSender;
  }

}
