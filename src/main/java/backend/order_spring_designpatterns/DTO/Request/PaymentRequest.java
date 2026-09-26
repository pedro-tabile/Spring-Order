package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.PaymentMethodsEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/* Record responsável por definir o transporte de dados da requisição ao PaymentService, delimitando informações
específicas */
public record PaymentRequest(
        @NotNull(message = "Tipo de pagamento inválido! Opções: 'ESPECIE', 'DEBITO', 'CREDITO' ou 'PIX'")
        @Schema(example = "ESPECIE/DEBITO/CREDITO/PIX")
        PaymentMethodsEnum type
){
}
