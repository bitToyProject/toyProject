package kr.bora.api.user.config;

import kr.bora.api.user.jwt.JwtAccessDeniedHandler;
import kr.bora.api.user.jwt.JwtAuthenticationEntryPoint;
import kr.bora.api.user.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/h2-console/**", "/favicon.ico", "/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger*/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/auth/**", "/user/**/**", "/chat/**", "/todos/**/**", "/mail/**", "/oauth/**",
                        "/auth/**", "/user/**/**", "/chat/**", "/todos/**/**", "/mail/**", "/reply/**/**","/department","/team","/teams")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}