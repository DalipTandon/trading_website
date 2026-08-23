package tradingWebsite.example.TradingWebsite.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 16)
    private String password;

    @NotBlank
    private String name;
}
