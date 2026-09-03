package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir o transporte de dados da requisição ao OrderItemService, delimitando informações específicas
public record OrderItemRequestDTO(Long productId, Integer amount) {
}
