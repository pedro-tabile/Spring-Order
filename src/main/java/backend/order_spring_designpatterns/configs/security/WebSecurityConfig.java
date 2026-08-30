package backend.order_spring_designpatterns.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Habilita configurações de segurança personalizadas
@EnableWebSecurity
// Classe com configurações de segurança (Spring Security) relacionadas à autenticação e acesso aos endpoints
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // Desabilita a proteção do csrf (token)
                .csrf(AbstractHttpConfigurer::disable)
                // Define métodos/endpoints autorizados com base na hierarquia
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                /* Método de autenticação http basic - desnecessário devido ao endpoint de login e exposição do bean de
                AuthenticationManager */
                // .httpBasic(Customizer.withDefaults())

                // Gerenciamento de sessão como stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    // Retorna um authenticationManager para processamento de autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Codificação de senhas
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // Função hash de senha - implementa usando uma função BCrypt para permitir a comparação com o hash salvo no banco
        return new BCryptPasswordEncoder();
    }
}