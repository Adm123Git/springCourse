package ru.adm123.springCourse.lesson3.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.adm123.springCourse.lesson3.aspect.anno.LogExecuteTime;

@Aspect
@Component
public class AspectLog {

    @Around("@annotation(logExecuteTime)")
    public Object simpleBenchmark(ProceedingJoinPoint joinPoint,
                                  LogExecuteTime logExecuteTime) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executeTime = System.currentTimeMillis() - startTime;
        LogExecuteTime.TimeUnit timeUnit = logExecuteTime.value();
        long timeToLog = timeUnit == LogExecuteTime.TimeUnit.MILLIS
                ? executeTime
                : executeTime * 1_000;
        System.out.println("Method " + joinPoint.getSignature().getName() + " executed at " + timeToLog + " " + timeUnit.getUnitName());
        return result;
    }

}
