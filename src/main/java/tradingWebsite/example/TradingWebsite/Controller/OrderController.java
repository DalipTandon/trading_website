package tradingWebsite.example.TradingWebsite.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
// import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import tradingWebsite.example.TradingWebsite.DTO.OrderResponseDTO;
import tradingWebsite.example.TradingWebsite.DTO.PlaceOrderRequestDTO;
import tradingWebsite.example.TradingWebsite.Services.OrderService;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor 
public class OrderController {
     
    private final OrderService orderServ;

    @PostMapping("/buy")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestHeader ("X-User-Id") Long userId,@Valid @RequestBody PlaceOrderRequestDTO request){

        OrderResponseDTO order=orderServ.placeMarketBuyOrder(userId, request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(order);
    } 
    @PostMapping ("/sell")
    public  ResponseEntity<OrderResponseDTO> sellOrder(@RequestHeader ("X-User-Id") Long userId , @Valid @RequestBody  PlaceOrderRequestDTO request){
        OrderResponseDTO order=orderServ.placeMarketSellOrder(userId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
