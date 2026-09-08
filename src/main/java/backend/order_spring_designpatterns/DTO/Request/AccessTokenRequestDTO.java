package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir as informações necessárias para requisição de autenticação de serviço SMTP
public record AccessTokenRequestDTO(String grant_type, String client_id, String client_secret) {
}
