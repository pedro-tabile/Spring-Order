package backend.order_spring_designpatterns.DTO.Request;

// Record que representa remetente e destinatário de um email
public record FromToRequestDTO(String name, String email) {
}
