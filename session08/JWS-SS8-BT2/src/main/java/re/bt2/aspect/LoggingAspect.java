package re.bt2.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import re.bt2.entity.ErrorLog;
import re.bt2.repository.ErrorLogRepository;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ErrorLogRepository errorLogRepository;

    @AfterThrowing(
            pointcut = "execution(* re.bt2.service.impl.*.*(..))",
            throwing = "ex"
    )
    public void logError(JoinPoint joinPoint, Exception ex) {

        ErrorLog errorLog = ErrorLog.builder()
                .timestamp(LocalDateTime.now())
                .methodName(joinPoint.getSignature().getName())
                .exceptionMessage(ex.getMessage())
                .build();

        errorLogRepository.save(errorLog);

        System.out.println("Đã lưu log lỗi");
    }
}