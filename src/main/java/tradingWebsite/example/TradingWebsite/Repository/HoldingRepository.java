package tradingWebsite.example.TradingWebsite.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.HoldingEntity;

public interface HoldingRepository extends JpaRepository<HoldingEntity,Long>{
        List<HoldingEntity> findByUserId(Long userId);
        Optional<HoldingEntity> findByUserIdAndStockId(Long userId,Long StockId);
}
