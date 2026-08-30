package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.UserAuthRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.UserAuthRegisterRequestDTO;
import backend.order_spring_designpatterns.Exception.UsernameAlreadyInUseException;
import backend.order_spring_designpatterns.Service.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // Gerenciador de autenticação
    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserAuthService userDetailsService;

    public AuthController(UserAuthService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Endpoint personalizado para login, implementando um gerenciador de autenticação
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthRequestDTO dataAuth){
        // Representa login e senha do usuário
        var usernamePassword = new UsernamePasswordAuthenticationToken(dataAuth.username(),dataAuth.password());
        // Tentativa de autenticação com base nas informações passadas
        authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserAuthRegisterRequestDTO userAuthRegisterDTO){
        userDetailsService.save(userAuthRegisterDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Define tratamento para possível exceção lançada
    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity handleUsernameException(UsernameAlreadyInUseException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
