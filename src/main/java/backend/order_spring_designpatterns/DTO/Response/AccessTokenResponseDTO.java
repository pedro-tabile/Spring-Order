package backend.order_spring_designpatterns.DTO.Response;

// Record responsável por representar a resposta para a requisição de autenticação de serviço SMTP
public record AccessTokenResponseDTO(String access_token, String token_type, Integer expires_in ) {
}
