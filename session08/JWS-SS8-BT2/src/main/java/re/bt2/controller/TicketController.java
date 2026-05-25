package re.bt2.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import re.bt2.dto.BookTicketRequest;
import re.bt2.entity.Ticket;
import re.bt2.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public Ticket bookTicket(
            @Valid @RequestBody BookTicketRequest request
    ) {
        return ticketService.bookTicket(request);
    }

    @PostMapping("/cancel/{ticketId}")
    public String cancelTicket(
            @PathVariable Long ticketId
    ) {
        return ticketService.cancelTicket(ticketId);
    }
}
