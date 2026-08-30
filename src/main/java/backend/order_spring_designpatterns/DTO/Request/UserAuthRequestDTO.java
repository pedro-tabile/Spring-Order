package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir o transporte de dados de autenticação do usuário, delimitando informações específicas
public record UserAuthRequestDTO(String username, String password) {
}
