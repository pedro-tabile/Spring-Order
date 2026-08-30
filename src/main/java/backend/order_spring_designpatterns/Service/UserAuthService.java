package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.UserAuthRegisterRequestDTO;
import backend.order_spring_designpatterns.Entity.UserAuth;
import backend.order_spring_designpatterns.Model.UserAuthModel;
import backend.order_spring_designpatterns.Repository.UserAuthRepository;
import backend.order_spring_designpatterns.Exception.UsernameAlreadyInUseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Classe que implementa service que carrega/localiza usuário a partir do username
@Service
public class UserAuthService implements UserDetailsService {
    private final UserAuthRepository userAuthRepository;

    public UserAuthService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         var userModel = userAuthRepository.findByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException(username));
         return UserAuthModel.fromEntity(userModel);
    }

    public UserAuth save(UserAuthRegisterRequestDTO userAuthRegisterDTO) {
        if (userAuthRepository.findByUsername(userAuthRegisterDTO.username()).isPresent())
            throw new UsernameAlreadyInUseException("Username is already in use!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userAuthRegisterDTO.password());

        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(userAuthRegisterDTO.username());
        userAuth.setPassword(encryptedPassword);
        userAuth.setRole(userAuthRegisterDTO.role());

        return userAuthRepository.save(userAuth);
    }
}
