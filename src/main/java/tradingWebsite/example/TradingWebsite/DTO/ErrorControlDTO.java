package tradingWebsite.example.TradingWebsite.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorControlDTO {

    @NotBlank
    private String errorMessage;

    @NotBlank
    private String errorCode;
}
