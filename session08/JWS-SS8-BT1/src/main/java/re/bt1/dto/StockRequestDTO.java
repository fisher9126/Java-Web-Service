package re.bt1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockRequestDTO {

    @NotBlank(message = "SKU không được để trống")
    private String sku;

    @Positive(message = "Quantity phải > 0")
    private Integer quantity;
}