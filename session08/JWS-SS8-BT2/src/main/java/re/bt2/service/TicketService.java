package re.bt2.service;


import re.bt2.dto.BookTicketRequest;
import re.bt2.entity.Ticket;

public interface TicketService {

    Ticket bookTicket(BookTicketRequest request);

    String cancelTicket(Long ticketId);
}
