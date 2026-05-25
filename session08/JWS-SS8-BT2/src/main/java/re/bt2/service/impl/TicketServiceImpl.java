package re.bt2.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.bt2.dto.BookTicketRequest;
import re.bt2.entity.Flight;
import re.bt2.entity.Ticket;
import re.bt2.repository.FlightRepository;
import re.bt2.repository.TicketRepository;
import re.bt2.service.TicketService;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Ticket bookTicket(BookTicketRequest request) {

        Flight flight = flightRepository
                .findByFlightNumber(request.getFlightNumber())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy chuyến bay"));

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Hết vé");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        Ticket ticket = Ticket.builder()
                .passengerName(request.getPassengerName())
                .flightId(flight.getId())
                .status("BOOKED")
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public String cancelTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy vé"));

        ticket.setStatus("CANCELED");

        ticketRepository.save(ticket);

        return "Hủy vé thành công";
    }
}