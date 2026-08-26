package tradingWebsite.example.TradingWebsite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long id;
    private String symbol;
    private String companyName;
    private String exchange;
    private String sector;
    private Boolean isActive;
}
