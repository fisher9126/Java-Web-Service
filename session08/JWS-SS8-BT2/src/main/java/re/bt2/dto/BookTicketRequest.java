package re.bt2.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookTicketRequest {

    private String flightNumber;

    @NotBlank(message = "Passenger name không được để trống")
    private String passengerName;
}