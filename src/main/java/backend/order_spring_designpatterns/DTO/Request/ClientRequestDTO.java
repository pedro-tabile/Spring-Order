package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir o transporte de dados da requisição ao ClientService, delimitando informações específicas
public record ClientRequestDTO(String name, String email) {
}
