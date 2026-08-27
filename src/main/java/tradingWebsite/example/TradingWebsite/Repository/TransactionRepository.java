package tradingWebsite.example.TradingWebsite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findByUserId(Long id);
}
