package backend.order_spring_designpatterns.DTO.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

// Record responsável por definir o transporte de dados da requisição ao ProductService, delimitando informações específicas
public record ProductRequestDTO(
        @NotBlank(message = "Nome do produto inválido!")
        String name,

        @NotNull(message = "Preço inválido!")
        @Positive(message = "O valor deve ser maior que 0!")
        @DecimalMax(value = "99999.99", message = "O valor limite é R$ 99.999,99!")
        BigDecimal price,

        @NotNull(message = "Quantidade de estoque inválida!")
        @Positive(message = "O valor deve ser maior que 0!")
        BigDecimal stock
) {
}
