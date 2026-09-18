package backend.order_spring_designpatterns.DTO.Response;

/* Record responsável por definir a resposta enviada ao cliente no body em caso de erro no serviço de envio de email */
public record SendEmailErrorResponseDTO(String messageOrder, String messageEmail) {
}
