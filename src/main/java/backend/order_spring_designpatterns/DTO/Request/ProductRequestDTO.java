package backend.order_spring_designpatterns.DTO.Request;

import java.math.BigDecimal;

// Record responsável por definir o transporte de dados da requisição ao ProductService, delimitando informações específicas
public record ProductRequestDTO(String name, BigDecimal price, BigDecimal stock) {
}
