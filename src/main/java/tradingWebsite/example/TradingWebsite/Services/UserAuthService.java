package tradingWebsite.example.TradingWebsite.Services;

import java.math.BigDecimal;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.LoginRequestDTO;
import tradingWebsite.example.TradingWebsite.DTO.RegisterRequestDTO;
import tradingWebsite.example.TradingWebsite.DTO.UserResponseDTO;

import tradingWebsite.example.TradingWebsite.Entity.UserEntity;
import tradingWebsite.example.TradingWebsite.Entity.WalletEntity;
import tradingWebsite.example.TradingWebsite.Exceptions.EmailAlreadyExists;
import tradingWebsite.example.TradingWebsite.Repository.UserRepository;
import tradingWebsite.example.TradingWebsite.Repository.WalletRepository;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passEncode;
    private final WalletRepository walletRepo;

    @Transactional     //transactional here will make sure if the user creation is failed then the wallet should not be created only if the user is sucessfully created then only the wallet should be created
    public UserResponseDTO saveUserEntity(RegisterRequestDTO request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExists("This email already exists");
            // throw new RuntimeException("This email already exists");
        }
        //creating user
        UserEntity user=new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passEncode.encode(request.getPassword()));
        // user.setPassword(request.getPassword());
        user.setName(request.getName());

        
        UserEntity savedUser=userRepository.save(user);
        //while creating a user wallet should also auto generate

        WalletEntity userWallet=new WalletEntity();
        userWallet.setUser(savedUser);
        userWallet.setBalance(BigDecimal.ZERO);
        userWallet.setLockedBalance(BigDecimal.ZERO);

        walletRepo.save(userWallet);

        return new UserResponseDTO(
         savedUser.getId(),
         savedUser.getName(),
         savedUser.getEmail(),
         savedUser.getKycStatus(),
         savedUser.getRole(),
         savedUser.getCreatedAt()

        );
    }

    // public UserEntity findByEmail(LoginRequestDTO loginReq){
    //    UserEntity user= userRepository.findByEmail(loginReq.getEmail())
    //    .orElseThrow(()->
    //     new RuntimeException("Their exists no user with this email id")
    // );
    // return user;
    // }
    public Optional<UserEntity> findByEmail(LoginRequestDTO loginReq){
            return userRepository.findByEmail(loginReq.getEmail());
    }
    
}
