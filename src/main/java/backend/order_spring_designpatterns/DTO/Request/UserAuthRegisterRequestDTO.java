package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.RolesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/* Record responsável por definir o transporte de dados de novo registro de usuário, delimitando informações específicas */
public record UserAuthRegisterRequestDTO(
        @NotNull(message = "Username inválido!")
        @NotBlank(message = "Username inválido!")
        String username,

        @NotNull(message = "Senha inválida!")
        @NotBlank(message = "Senha inválida!")
        @Size(min = 6, max = 30, message = "A senha deve ter entre 6 e 30 caracteres!")
        String password,

        RolesEnum role
) {
}
