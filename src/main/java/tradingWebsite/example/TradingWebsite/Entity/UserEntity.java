package tradingWebsite.example.TradingWebsite.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String name;

@Column(unique = true,nullable = false)
private String email;

@Column(nullable = false)
private String password;

@Column(nullable = false)
private String role="USER";

@Column(nullable = false)
private String KycStatus="PENDING";

@Column(nullable = false)
private Boolean is2FAEnabled=false; 

@Column(nullable = false)
private LocalDateTime createdAt=LocalDateTime.now();

@Column(nullable = false)
private LocalDateTime updatedAt=LocalDateTime.now();
}
