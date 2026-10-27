package tradingWebsite.example.TradingWebsite.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

// import org.springframework.cglib.core.Local;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.OrderResponseDTO;
import tradingWebsite.example.TradingWebsite.DTO.PlaceOrderRequestDTO;
import tradingWebsite.example.TradingWebsite.Entity.HoldingEntity;
import tradingWebsite.example.TradingWebsite.Entity.OrderEntity;
import tradingWebsite.example.TradingWebsite.Entity.StockEntity;
import tradingWebsite.example.TradingWebsite.Entity.TransactionEntity;
import tradingWebsite.example.TradingWebsite.Entity.UserEntity;
import tradingWebsite.example.TradingWebsite.Entity.WalletEntity;
import tradingWebsite.example.TradingWebsite.Exceptions.InsufficientFundException;
// import tradingWebsite.example.TradingWebsite.Exceptions.InsufficientHoldingsException;
import tradingWebsite.example.TradingWebsite.Exceptions.ResourceNotFound;
import tradingWebsite.example.TradingWebsite.Repository.HoldingRepository;
import tradingWebsite.example.TradingWebsite.Repository.OrderRepository;
import tradingWebsite.example.TradingWebsite.Repository.StockRepository;
import tradingWebsite.example.TradingWebsite.Repository.TransactionRepository;
import tradingWebsite.example.TradingWebsite.Repository.UserRepository;
import tradingWebsite.example.TradingWebsite.Repository.WalletRepository;

@Service 
@AllArgsConstructor 
public class OrderService {
    private OrderRepository orderRepo;
    private StockRepository stockRepo;
    private HoldingRepository holdingRepo;
    private TransactionRepository transactionRepo;
    private WalletRepository walletRepo;
    private UserRepository userRepo;

    @Transactional
    public OrderResponseDTO placeMarketBuyOrder(Long userId,PlaceOrderRequestDTO orderPlace){
        UserEntity user=userRepo.findById(userId)
                        .orElseThrow(()->new ResourceNotFound("User Not Found"));

        StockEntity stock=stockRepo.findBySymbol(orderPlace.getSymbol())
                        .orElseThrow(()->new ResourceNotFound("Stock not found"));

        WalletEntity wallet=walletRepo.findByUserId(userId)
                        .orElseThrow(()->new ResourceNotFound("Wallet not found"));
        BigDecimal executionPrice=stock.getCurrentPrice();
        BigDecimal totalCost=executionPrice.multiply(BigDecimal.valueOf(orderPlace.getQuantity()));

        if(wallet.getBalance().compareTo(totalCost)<0){
            throw new InsufficientFundException("Insufficient Fund , required fund -> "+totalCost+" Available Fund ->" + wallet.getBalance());
        }
        wallet.setBalance(wallet.getBalance().subtract(totalCost));
        wallet.setCreatedAt(LocalDateTime.now());
        walletRepo.save(wallet);

        OrderEntity order=new OrderEntity();
            order.setUser(user);
            order.setStock(stock);
            order.setOrderType("MARKET");
            order.setSide("BUY");
            order.setQuantity(orderPlace.getQuantity());
            order.setExecutedPrice(executionPrice);
            order.setStatus("EXECUTED");
            order.setExecutedAt(LocalDateTime.now());
        OrderEntity savedOrder=orderRepo.save(order);
        // orderRepo.save(order);
        HoldingEntity holding=holdingRepo.findByUserIdAndStockId(userId, stock.getId())
                               .orElseGet(()->{
                                HoldingEntity newHolding=new HoldingEntity();
                                // newHolding.setId(userId);
                                newHolding.setUser(user);
                                newHolding.setStock(stock);
                                newHolding.setAvgBuyPrice(BigDecimal.ZERO);
                                newHolding.setQuantity(0);
                                return newHolding;
                            });

        int oldQty=holding.getQuantity();
        BigDecimal oldAvgPrice=holding.getAvgBuyPrice();
        int newQty=oldQty+orderPlace.getQuantity();
        
         BigDecimal oldTotalValue = oldAvgPrice.multiply(BigDecimal.valueOf(oldQty));
        BigDecimal newTotalValue = executionPrice.multiply(BigDecimal.valueOf(orderPlace.getQuantity()));
        BigDecimal newAvgPrice = oldTotalValue.add(newTotalValue)
            .divide(BigDecimal.valueOf(newQty), 2, java.math.RoundingMode.HALF_UP);
            
            holding.setAvgBuyPrice(newAvgPrice);
            holding.setQuantity(newQty);
            holding.setCreatedAt(LocalDateTime.now());
            holdingRepo.save(holding);

            TransactionEntity transaction=new TransactionEntity();
            transaction.setUser(user);
            transaction.setOrder(savedOrder);
            transaction.setType("BUY");
            transaction.setStatus("SUCCESS");
            transaction.setAmount(totalCost);
            transaction.setBalanceAfter(wallet.getBalance());
            
            transactionRepo.save(transaction);

        return new OrderResponseDTO(
            savedOrder.getId(),
            stock.getSymbol(),
            savedOrder.getOrderType(),
            savedOrder.getSide(),
            savedOrder.getQuantity(),
            savedOrder.getExecutedPrice(),
            savedOrder.getStatus(),
            savedOrder.getExecutedAt()
        );
    }

    @Transactional 
    public OrderResponseDTO placeMarketSellOrder(Long userId, PlaceOrderRequestDTO orderPlace){
        UserEntity user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("USER NOT FOUND"));

        StockEntity stock=stockRepo.findBySymbol(orderPlace.getSymbol()).orElseThrow(()->new ResourceNotFound("STOCK NOT FOUND"));

        WalletEntity wallet=walletRepo.findByUserId(userId).orElseThrow(()->new ResourceNotFound("NO WALLER FOUND"));
        HoldingEntity holding=holdingRepo.findByUserIdAndStockId(userId,stock.getId()).orElseThrow(()->new ResourceNotFound("HOLDING NOT FOUND"));
        int sellQuantity=orderPlace.getQuantity();
        if(sellQuantity<=0){
          throw new IllegalArgumentException(
                "Sell quantity must be greater than zero");
        }
        if(holding.getQuantity()<sellQuantity){
            throw new IllegalArgumentException(
                "Insufficient stock holdings");
        }
        BigDecimal executionPrice=stock.getCurrentPrice();
        BigDecimal totalSellingValue=executionPrice.multiply(BigDecimal.valueOf(sellQuantity));

        int remainingHolding=holding.getQuantity()-sellQuantity;
        holding.setQuantity(remainingHolding);
        holdingRepo.save(holding);

        wallet.setBalance(wallet.getBalance().add(totalSellingValue));
        walletRepo.save(wallet);

        OrderEntity order=new OrderEntity();
        order.setUser(user);
        order.setStock(stock);
        order.setOrderType("MARKET");
        order.setSide("SELL");
        order.setQuantity(sellQuantity);
        order.setLimitPrice(totalSellingValue);
        order.setStatus("SUCCESSFUL");
        order.setExecutedPrice(executionPrice);
        order.setExecutedAt(LocalDateTime.now());

        OrderEntity savedOrder=orderRepo.save(order);
         TransactionEntity transaction = new TransactionEntity();

        transaction.setUser(user);
        transaction.setOrder(savedOrder);
        transaction.setType("SELL");
        transaction.setStatus("SUCCESS");
        transaction.setAmount(totalSellingValue);
        transaction.setBalanceAfter(wallet.getBalance());

      transactionRepo.save(transaction);

         return new OrderResponseDTO(
            savedOrder.getId(),
            stock.getSymbol(),
            savedOrder.getOrderType(),
            savedOrder.getSide(),
            savedOrder.getQuantity(),
            savedOrder.getExecutedPrice(),
            savedOrder.getStatus(),
            savedOrder.getExecutedAt()
    );
    }
}
