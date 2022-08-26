package kr.bora.api.common.aop;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ParameterAOP {
//
//    @Pointcut("execution(* kr.bora.api.*.controller..*.*(..))")
//    private void cut() {
//
//    }
//
//    //파라미터 입력 값
//    @Before("cut()")
//    public void before(JoinPoint joinPoint) throws JsonProcessingException {
//        Object[] args = joinPoint.getArgs();
//        for (Object obj : args) {
//            log.info("type : " + obj.getClass().getSimpleName());
//        log.info("value : " + new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj));
//        }
//    }
//
//    // 파라미터 입력 후 호출 결과 값
//    @AfterReturning(value = "cut()", returning = "returnObj")
//    public void afterReturn(Object returnObj) throws JsonProcessingException {
//        log.info("return obj");
//        log.info(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(returnObj));
//    }
}

