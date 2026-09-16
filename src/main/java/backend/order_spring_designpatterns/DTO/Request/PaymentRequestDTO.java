package backend.order_spring_designpatterns.DTO.Request;

import jakarta.validation.constraints.NotBlank;

// Record responsável por definir o transporte de dados da requisição ao PaymentService, delimitando informações específicas
public record PaymentRequestDTO(
        @NotBlank(message = "Tipo de pagamento inválido!")
        String type
){
}
