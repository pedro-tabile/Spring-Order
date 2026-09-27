package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Service.Enums.RolesEnum;

/* Record responsável por definir o transporte de dados de um usuário registrado, delimitando informações específicas */
public record UserAuthResponse(String username, RolesEnum role) {
}
