package tradingWebsite.example.TradingWebsite.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDTO {
    private Long id;
    private String userName;
    private String email;
    private BigDecimal balance;
    private BigDecimal lockedBalance;
    private LocalDateTime updated_at;
}
