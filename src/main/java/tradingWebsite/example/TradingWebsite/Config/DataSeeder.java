package tradingWebsite.example.TradingWebsite.Config;

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
            seedStock("RELIANCE", "Reliance Industries", "NSE", "Energy",true);
            seedStock("TCS", "Tata Consultancy Services", "NSE", "IT",true);
            seedStock("INFY", "Infosys", "NSE", "IT",false);
            seedStock("HDFCBANK", "HDFC Bank", "NSE", "Banking",true);
            seedStock("ICICIBANK", "ICICI Bank", "NSE", "Banking",false);
        }
    }
    private void seedStock(String symbol, String name, String exchange, String sector,Boolean isActive) {
        StockEntity stock = new StockEntity();
        stock.setSymbol(symbol);
        stock.setCompanyName(name);
        stock.setExchange(exchange);
        stock.setSector(sector);
        stock.setIsActive(isActive);
        stockRepo.save(stock);
    }
}
