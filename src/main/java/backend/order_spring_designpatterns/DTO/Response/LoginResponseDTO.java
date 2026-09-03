package backend.order_spring_designpatterns.DTO.Response;

// Record responsável por definir o transporte de token do endpoint de login como resposta ao cliente
public record LoginResponseDTO(String token) {
}
