package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Model.UserModel;
import backend.order_spring_designpatterns.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         var userModel = userRepository.findByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException(username));
         return UserModel.fromEntity(userModel);
    }

    // Codificação de senhas
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // Função hash de senha
        return new BCryptPasswordEncoder();
    }
}
