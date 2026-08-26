package tradingWebsite.example.TradingWebsite.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

    Optional<StockEntity> findBySymbol(String symbol);
} 
