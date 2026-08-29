package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.AuthRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid AuthRequestDTO dataAuth){
        return ResponseEntity.ok().build();
    }
}
