package backend.order_spring_designpatterns.DTO.Request;

import jakarta.validation.constraints.NotNull;

// Record responsável por definir o transporte de dados de autenticação do usuário, delimitando informações específicas
public record UserAuthRequestDTO(
        @NotNull(message = "Username inválido!")
        String username,

        @NotNull(message = "Senha inválida!")
        String password
) {
}
