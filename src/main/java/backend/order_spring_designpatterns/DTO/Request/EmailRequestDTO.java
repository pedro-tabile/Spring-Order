package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir as informações padrão dos emails
public record EmailRequestDTO(String text, String html, String subject, FromToRequestDTO from, FromToRequestDTO to) {
}
