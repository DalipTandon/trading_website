package tradingWebsite.example.TradingWebsite.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.ErrorControlDTO;
import tradingWebsite.example.TradingWebsite.DTO.LoginRequestDTO;
import tradingWebsite.example.TradingWebsite.DTO.RegisterRequestDTO;
import tradingWebsite.example.TradingWebsite.DTO.UserResponseDTO;
import tradingWebsite.example.TradingWebsite.Entity.UserEntity;
import tradingWebsite.example.TradingWebsite.Services.UserAuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuth;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request){

        UserResponseDTO user=userAuth.saveUserEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginRequestDTO loginRequest ){
    //    UserEntity user=userAuth.findByEmail(loginRequest);
        Optional<UserEntity> userOptional =userAuth.findByEmail(loginRequest);
        if(userOptional.isEmpty()){
     ErrorControlDTO error=new ErrorControlDTO(
            "Invalid Credentail",
            "401"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        UserEntity user=userOptional.get();
    if (!passwordEncoder.matches(
            loginRequest.getPassword(),
            user.getPassword())) {

        ErrorControlDTO error = new ErrorControlDTO(
                "INVALID_CREDENTIALS",
                "Invalid email or password"
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
