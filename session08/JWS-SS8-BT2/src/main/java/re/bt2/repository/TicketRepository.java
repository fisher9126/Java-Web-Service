package re.bt2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.bt2.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
