package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.UpdateUserAuthRequest;
import backend.order_spring_designpatterns.DTO.Request.UserAuthRegisterRequest;
import backend.order_spring_designpatterns.DTO.Request.UserAuthRequest;
import backend.order_spring_designpatterns.Entity.UserAuth;
import backend.order_spring_designpatterns.Exception.InvalidPassword;
import backend.order_spring_designpatterns.Model.UserAuthModel;
import backend.order_spring_designpatterns.Repository.UserAuthRepository;
import backend.order_spring_designpatterns.Exception.UsernameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* Classe que implementa service que define identificação de usuário a partir do username. Também define outras
operações CRUD */
@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return UserAuthModel.fromEntity(userAuth);
    }

    public UserAuth register(UserAuthRegisterRequest userAuthRegisterDTO) {
        if (userAuthRepository.findByUsername(userAuthRegisterDTO.username()).isPresent())
            throw new UsernameAlreadyInUseException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userAuthRegisterDTO.password());

        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(userAuthRegisterDTO.username());
        userAuth.setPassword(encryptedPassword);
        if (userAuthRegisterDTO.role() != null)
            userAuth.setRole(userAuthRegisterDTO.role());

        return userAuthRepository.save(userAuth);
    }

    public void delete(UserAuthRequest userAuthRequest) throws UsernameNotFoundException{
        UserDetails userAuth = loadUserByUsername(userAuthRequest.username());
        UserAuthModel userAuthModel = (UserAuthModel) userAuth;

        if (!BCrypt.checkpw(userAuthRequest.password(), userAuth.getPassword()))
            throw new InvalidPassword();

        userAuthRepository.deleteById(UUID.fromString(userAuthModel.getUserId()));
    }

    public UserAuth update(UpdateUserAuthRequest updateUserAuthRequest){
        UserDetails currentUser = loadUserByUsername(updateUserAuthRequest.oldUsername());
        UserAuthModel userAuthModel = (UserAuthModel) currentUser;

        if (!BCrypt.checkpw(updateUserAuthRequest.oldPassword(), userAuthModel.getPassword()))
            throw new InvalidPassword();

        UserAuth newDataUser = new UserAuth();
        newDataUser.setUserId(UUID.fromString(userAuthModel.getUserId()));
        newDataUser.setUsername(updateUserAuthRequest.newUsername());

        String encryptedPassword = new BCryptPasswordEncoder().encode(updateUserAuthRequest.newPassword());
        newDataUser.setPassword(encryptedPassword);

        if (updateUserAuthRequest.newRole() != null)
            newDataUser.setRole(updateUserAuthRequest.newRole());
        else
            newDataUser.setRole(userAuthModel.getRole());

        return userAuthRepository.save(newDataUser);
    }
}
