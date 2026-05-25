package re.jwsss8bt3.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RefundRequestDTO {

    @Pattern(
            regexp = "^[A-Z0-9]+$",
            message = "Transaction code không hợp lệ"
    )
    private String transactionCode;

    @NotNull
    private Double amount;
}
