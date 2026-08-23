package tradingWebsite.example.TradingWebsite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.RegisterRequestDTO;
import tradingWebsite.example.TradingWebsite.Entity.UserEntity;
import tradingWebsite.example.TradingWebsite.Repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passEncode;
    public UserEntity saveUserEntity(RegisterRequestDTO request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("This email already exists");
        }
        
        UserEntity user=new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passEncode.encode(request.getPassword()));
        // user.setPassword(request.getPassword());
        user.setName(request.getName());

        UserEntity savedUser=userRepository.save(user);
        return savedUser;
    }
}
