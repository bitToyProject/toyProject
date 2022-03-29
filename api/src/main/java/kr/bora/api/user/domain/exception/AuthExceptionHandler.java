package kr.bora.api.user.domain.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
@Data
public class AuthExceptionHandler implements AuthenticationFailureHandler {
    private String loginIdName;
    private String loginPwd;
    private String errorMsg;

    //TODO : 프론트 엔드 뷰 url 이 고정되면 설정할 예정.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter(loginIdName);
        String password = request.getParameter(loginPwd);
        String error = null;

        if(exception instanceof BadCredentialsException) {
            error = "error.BadCredentials";
        } else if(exception instanceof InternalAuthenticationServiceException) {
            error = "error.BadCredentials";
        } else if(exception instanceof DisabledException) {
            error = "error.Disaled";
        } else if(exception instanceof CredentialsExpiredException) {
            error = "error.CredentialsExpired";
        }

        request.setAttribute(loginIdName, username);
        request.setAttribute(loginPwd, password);
        request.setAttribute(errorMsg, error);
        log.error("login error : %d",request);
    }

}
