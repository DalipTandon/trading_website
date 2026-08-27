package tradingWebsite.example.TradingWebsite.Config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.Entity.StockEntity;
import tradingWebsite.example.TradingWebsite.Repository.StockRepository;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final StockRepository stockRepo;

    @Override
    public void run(String...args){
        if (stockRepo.count() == 0) {
            seedStock("RELIANCE", "Reliance Industries", "NSE", "Energy",true,BigDecimal.valueOf(2850.00));
            seedStock("TCS", "Tata Consultancy Services", "NSE", "IT",true,BigDecimal.valueOf(3650.00));
            seedStock("INFY", "Infosys", "NSE", "IT",false,BigDecimal.valueOf(1580.00));
            seedStock("HDFCBANK", "HDFC Bank", "NSE", "Banking",true,BigDecimal.valueOf(1620.00));
            seedStock("ICICIBANK", "ICICI Bank", "NSE", "Banking",false,BigDecimal.valueOf(1145.00));
        }
    }
    private void seedStock(String symbol, String name, String exchange, String sector,Boolean isActive,BigDecimal currentPrice) {
        StockEntity stock = new StockEntity();
        stock.setSymbol(symbol);
        stock.setCompanyName(name);
        stock.setExchange(exchange);
        stock.setSector(sector);
        stock.setIsActive(isActive);
        stock.setCurrentPrice(currentPrice);
        stockRepo.save(stock);
    
    }
}
