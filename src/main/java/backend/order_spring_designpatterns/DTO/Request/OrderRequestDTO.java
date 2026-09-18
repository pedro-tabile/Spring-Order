package backend.order_spring_designpatterns.DTO.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// Classe responsável por definir o transporte de dados da requisição ao OrderService, delimitando informações específicas
public record OrderRequestDTO (
        @NotNull(message = "O Id do cliente não pode ser nulo!")
        Long clientId,

        @NotEmpty(message = "O pedido deve apresentar algum item!")
        @Valid List<OrderItemRequestDTO> orderItems,

        @Valid PaymentRequestDTO payment
) {
}
