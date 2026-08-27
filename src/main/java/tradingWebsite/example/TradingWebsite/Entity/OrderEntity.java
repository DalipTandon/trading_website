package tradingWebsite.example.TradingWebsite.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Collate;

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
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "stock_id",nullable = false)
    private StockEntity stock;

    @Column(nullable = false)
    private String orderType; //MARKET,LIMIT

    @Column(nullable = false)
    private String side;  //BUY or SELL

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 15,scale = 2)
    private BigDecimal limitPrice;

    @Column(nullable = false)
    private String status="PENDING";

    @Column(nullable = false)
    private BigDecimal executedPrice;

    @Column(nullable = false)
    private LocalDateTime executedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

}
