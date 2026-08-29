package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Product;

import java.math.BigDecimal;

/* Record responsável por definir o transporte de dados do ProductService ao controller do Product, delimitando informações
específicas para resposta à requisição */
public record ProductResponseDTO(Long id, String name, BigDecimal price, BigDecimal stock) {
    public ProductResponseDTO(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
