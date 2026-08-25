package tradingWebsite.example.TradingWebsite.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "User_id",nullable = false,unique = true)
    private UserEntity user;

    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal balance=BigDecimal.ZERO;

    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal lockedBalance=BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
}
