package re.bt2.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import re.bt2.entity.Flight;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);
}