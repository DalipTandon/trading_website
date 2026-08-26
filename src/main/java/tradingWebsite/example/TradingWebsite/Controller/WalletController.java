package tradingWebsite.example.TradingWebsite.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.WalletResponseDTO;
import tradingWebsite.example.TradingWebsite.Entity.WalletEntity;
import tradingWebsite.example.TradingWebsite.Repository.WalletRepository;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletRepository walletRepo;
    @GetMapping("/getWallet")
    private WalletResponseDTO getWallet(@RequestHeader("X-User-Id") Long userId){
        WalletEntity wallet= walletRepo.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("Wallet not found"));

        return new WalletResponseDTO(
            wallet.getId(),
            wallet.getUser().getName(),
            wallet.getUser().getEmail(),
            wallet.getBalance(),
            wallet.getLockedBalance(), 
            wallet.getUser().getUpdatedAt()
        );
    }
}
