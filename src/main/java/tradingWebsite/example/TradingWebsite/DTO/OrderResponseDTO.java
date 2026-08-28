package tradingWebsite.example.TradingWebsite.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;
    private String symbol;
    private String orderType;
    private String side;
    private Integer quantity;
    private BigDecimal executedPrice;
    private String status;
    private LocalDateTime executedAt;
}
