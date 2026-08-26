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
            seedStock("RELIANCE", "Reliance Industries", "NSE", "Energy");
            seedStock("TCS", "Tata Consultancy Services", "NSE", "IT");
            seedStock("INFY", "Infosys", "NSE", "IT");
            seedStock("HDFCBANK", "HDFC Bank", "NSE", "Banking");
            seedStock("ICICIBANK", "ICICI Bank", "NSE", "Banking");
        }
    }
    private void seedStock(String symbol, String name, String exchange, String sector) {
        StockEntity stock = new StockEntity();
        stock.setSymbol(symbol);
        stock.setCompanyName(name);
        stock.setExchange(exchange);
        stock.setSector(sector);
        stockRepo.save(stock);
    }
}
