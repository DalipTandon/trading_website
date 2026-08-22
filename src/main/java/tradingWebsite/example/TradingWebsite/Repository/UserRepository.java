package tradingWebsite.example.TradingWebsite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
}
