package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.RolesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/* Record responsável por definir o transporte de dados de novo registro de usuário, delimitando informações específicas */
public record UserAuthRegisterRequest(
        @NotBlank(message = "Username inválido!")
        String username,

        @NotBlank(message = "Senha inválida!")
        @Size(min = 3, max = 30, message = "A senha deve ter entre 3 e 30 caracteres!")
        String password,

        RolesEnum role
) {
}
