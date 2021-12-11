package kr.bora.api.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
@Aspect
@Slf4j
public class LogAspect {
    //메서드 호출 실행 시간
    @Around("execution(* kr.bora.api.*.controller..*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();
        log.info("걸린 시간 :" + stopWatch.getLastTaskTimeMillis() + "ms");
//        log.info(stopWatch.prettyPrint());

        return proceed;
    }

    @Around("execution(* kr.bora.api.*.controller..*.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        if (name.contains("Controller"))
            type = "Controller 호출 Method ===>";

            log.info(type + name + "," + joinPoint.getSignature().getName() + "()");

            return joinPoint.proceed();
    }
}
