package re.bt1.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("""
        execution(* re.bt1.service.impl
        .ProductServiceImpl.inspectInventory(..))
    """)
    public Object measureExecutionTime(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                "inspectInventory executed in "
                        + (end - start)
                        + " ms"
        );

        return result;
    }
}