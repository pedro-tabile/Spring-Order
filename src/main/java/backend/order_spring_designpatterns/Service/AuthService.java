package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Configs.security.TokenService;
import backend.order_spring_designpatterns.DTO.Request.UserAuthRequest;
import backend.order_spring_designpatterns.Entity.UserAuth;
import backend.order_spring_designpatterns.Model.UserAuthModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
/* Classe que define as regras para autenticação do usuário */
/* A autenticação não pode ser realizada no UserAuthService porque haveria uma dependência circular: o processo de
login (antes no UserAuthService) utiliza o AuthenticationManager que, por sua vez, precisa de uma classe que implemente
UserDetailsService para o mecanismo de autenticação, e essa classe é o UserAuthService. Desse modo, caso permanecessem
no mesmo lugar, o UserAuthService dependeria do AuthenticationManager, enquanto o AuthenticationManager dependeria do
UserAuthService/UserDetailsService durante o processo de autenticação. Portanto, destinou-se a essa classe o serviço de
login. */
public class AuthService {
    // Gerenciador de autenticação
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String validateLoginAndGenerateToken(UserAuthRequest userAuthRequest){
        // Representa login e senha do usuário
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                userAuthRequest.username(),
                userAuthRequest.password()
        );

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

        return token;
    }
}