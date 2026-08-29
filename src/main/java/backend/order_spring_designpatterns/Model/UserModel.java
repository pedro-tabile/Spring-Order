package backend.order_spring_designpatterns.Model;

import backend.order_spring_designpatterns.Entity.UserAuth;
import backend.order_spring_designpatterns.Service.Enums.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/* Serializable é a capacidade de transformar um objeto da memória em uma sequência de bytes ou em um formato plano
(como JSON ou texto) para salvar o estado do objeto a fim de  transmiti-lo (em arquivo, envio por rede ou entre diferentes
sistemas), permitindo recriá-lo por meio da desserialização.*/
// UserDetails representa as informações de usuário durante a autenticação
@Data
@AllArgsConstructor
public class UserModel implements Serializable, UserDetails {
    // Auxilia o compilador para identificar que faz parte de um mecanismo de serialização
    @Serial
    private static final long serialVersionUID = 1L;

    private String userId, username, password;
    private RolesEnum role;

    public static UserModel fromEntity(UserAuth userAuth) {
        return new UserModel(
                userAuth.getUserId().toString(),
                userAuth.getUsername(),
                userAuth.getPassword(),
                userAuth.getRole()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
