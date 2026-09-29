package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.RolesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

/* Record responsável por definir o transporte de dados para alteração de informações de um usuário, delimitando
informações necessárias para validação e alteração */
public record UpdateUserAuthRequest(
        @NotBlank(message = "Username inválido!")
        String oldUsername,

        @NotBlank(message = "Senha inválida!")
        @Size(min = 3, max = 30, message = "A senha deve ter entre 3 e 30 caracteres!")
        String oldPassword,

        @NotBlank(message = "Username inválido!")
        String newUsername,

        @NotBlank(message = "Senha inválida!")
        @Size(min = 3, max = 30, message = "A senha deve ter entre 3 e 30 caracteres!")
        String newPassword,

        RolesEnum newRole
){
}