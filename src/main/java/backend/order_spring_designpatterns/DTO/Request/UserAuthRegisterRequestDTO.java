package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.RolesEnum;

/* Record responsável por definir o transporte de dados de novo registro de usuário, delimitando informações específicas */
public record UserAuthRegisterRequestDTO(String username, String password, RolesEnum role) {
}
