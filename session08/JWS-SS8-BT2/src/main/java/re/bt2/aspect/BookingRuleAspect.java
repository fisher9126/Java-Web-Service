package re.bt2.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import re.bt2.entity.Flight;
import re.bt2.entity.Ticket;
import re.bt2.repository.FlightRepository;
import re.bt2.repository.TicketRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class BookingRuleAspect {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Before("execution(* re.bt2.service.impl.TicketServiceImpl.cancelTicket(..))")
    public void checkCancelRule(JoinPoint joinPoint) {

        Long ticketId = (Long) joinPoint.getArgs()[0];

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy vé"));

        Flight flight = flightRepository.findById(ticket.getFlightId())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy chuyến bay"));

        long hours = Duration.between(
                LocalDateTime.now(),
                flight.getDepartureTime()
        ).toHours();

        if (hours < 24) {
            throw new RuntimeException(
                    "Không thể hủy vé trước giờ bay dưới 24h");
        }
    }
}