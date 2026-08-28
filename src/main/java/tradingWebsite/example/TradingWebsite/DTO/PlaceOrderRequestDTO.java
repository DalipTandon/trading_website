package tradingWebsite.example.TradingWebsite.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PlaceOrderRequestDTO {

    @NotBlank
    private String symbol;

    @NotBlank
    private String side;

    @NotNull
    @Positive
    private Integer quantity;
}
