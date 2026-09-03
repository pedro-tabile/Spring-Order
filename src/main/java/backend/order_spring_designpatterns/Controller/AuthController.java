package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.UserAuthRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.UserAuthRegisterRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.LoginResponseDTO;
import backend.order_spring_designpatterns.Entity.UserAuth;
import backend.order_spring_designpatterns.Exception.UsernameAlreadyInUseException;
import backend.order_spring_designpatterns.Model.UserAuthModel;
import backend.order_spring_designpatterns.Service.UserAuthService;
import backend.order_spring_designpatterns.configs.security.TokenService;
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

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // Gerenciador de autenticação
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthService userDetailsService;

    // Endpoint personalizado para camada/tratamento de login, implementando um gerenciador de autenticação
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserAuthRequestDTO dataAuth){
        // Representa login e senha do usuário
        var usernamePassword = new UsernamePasswordAuthenticationToken(dataAuth.username(),dataAuth.password());
        // Tentativa de autenticação com base nas informações passadas
        var auth = authenticationManager.authenticate(usernamePassword);

        // Geração de token; retorno do mesmo como resposta à requisição
        var userAuthentication = (UserAuthModel) auth.getPrincipal();
        var token = tokenService.generateToken(
                new UserAuth(
                        UUID.fromString(userAuthentication.getUserId()),
                        userAuthentication.getUsername(),
                        userAuthentication.getPassword(),
                        userAuthentication.getRole()
                )
        );

        LoginResponseDTO loginResponse = new LoginResponseDTO(token);

        return ResponseEntity.ok(loginResponse);
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
