package tradingWebsite.example.TradingWebsite.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tradingWebsite.example.TradingWebsite.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
