package backend.order_spring_designpatterns.DTO.Response;

// Record representando a resposta recebida pela API SendPulse após requisição de envio de email
public record EmailResponseDTO(String id, boolean result) {
}
