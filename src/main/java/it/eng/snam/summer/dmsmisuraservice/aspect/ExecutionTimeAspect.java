package it.eng.snam.summer.dmsmisuraservice.aspect;

import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(it.eng.snam.summer.dmsmisuraservice.aspect.ExecutionTime)")
    public Object log(ProceedingJoinPoint p) throws Throwable {

        Long start = Instant.now().toEpochMilli();
        try {
            return p.proceed();
        } finally {
            Long end = Instant.now().toEpochMilli();
            System.out.println("Executing method: " + p.getSignature().getDeclaringTypeName() + "."
                    + p.getSignature().getName() + " , execution time: " + (end - start) + " ms");
        }
    }

}
