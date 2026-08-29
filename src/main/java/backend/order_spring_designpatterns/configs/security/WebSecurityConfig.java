package backend.order_spring_designpatterns.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                // Métodos/endpoints autorizados somente para usuários autenticados
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                // Método de autenticação http basic
                .httpBasic(Customizer.withDefaults())
                // Gerenciamento de sessão stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}