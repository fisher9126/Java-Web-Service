package re.bt2.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import re.bt2.dto.BookTicketRequest;

@Aspect
@Component
public class SanitizationAspect {

    @Around("execution(* re.bt2.service.impl.TicketServiceImpl.bookTicket(..))")
    public Object sanitizePassengerName(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        BookTicketRequest request = (BookTicketRequest) args[0];

        String cleanedName = request.getPassengerName()
                .trim()
                .toUpperCase();

        request.setPassengerName(cleanedName);

        args[0] = request;

        System.out.println("Tên sau sanitize: " + cleanedName);

        return joinPoint.proceed(args);
    }
}