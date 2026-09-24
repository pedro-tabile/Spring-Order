package backend.order_spring_designpatterns.DTO.Request;

// Record que representa remetente e destinatário de um email
public record FromToRequest(String name, String email) {
}
