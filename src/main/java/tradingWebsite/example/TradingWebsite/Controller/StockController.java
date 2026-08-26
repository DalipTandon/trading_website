package tradingWebsite.example.TradingWebsite.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.Entity.StockEntity;
import tradingWebsite.example.TradingWebsite.Repository.StockRepository;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepo;
    @GetMapping("/getAllStocks")
    private List<StockEntity> getAllStocks(){
        return stockRepo.findAll();
    }

}
