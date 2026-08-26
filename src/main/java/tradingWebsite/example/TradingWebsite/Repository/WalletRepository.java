package tradingWebsite.example.TradingWebsite.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.WalletEntity;


public interface WalletRepository extends JpaRepository<WalletEntity,Long> {
    Optional<WalletEntity> findByUserId(Long id);
}
