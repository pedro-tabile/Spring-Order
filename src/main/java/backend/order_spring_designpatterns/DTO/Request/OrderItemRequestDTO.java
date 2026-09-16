package backend.order_spring_designpatterns.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Record responsável por definir o transporte de dados da requisição ao OrderItemService, delimitando informações específicas
public record OrderItemRequestDTO(
        @NotNull(message = "O Id do produto não pode ser nulo!")
        Long productId,

        @Positive(message = "A quantidade deve ser maior que 0!")
        Integer amount
) {
}
