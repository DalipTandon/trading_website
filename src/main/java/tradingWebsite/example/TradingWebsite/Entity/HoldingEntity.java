package tradingWebsite.example.TradingWebsite.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "holdings")
@NoArgsConstructor
@AllArgsConstructor
public class HoldingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "stock_id",nullable = false)
    private StockEntity stock;

    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal avgBuyPrice=BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
}
