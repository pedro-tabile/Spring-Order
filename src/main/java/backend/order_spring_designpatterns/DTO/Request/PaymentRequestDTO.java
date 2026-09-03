package backend.order_spring_designpatterns.DTO.Request;

// Record responsável por definir o transporte de dados da requisição ao PaymentService, delimitando informações específicas
public record PaymentRequestDTO(String type){
}
