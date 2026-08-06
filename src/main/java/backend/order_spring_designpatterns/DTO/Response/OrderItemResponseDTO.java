package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.OrderItem;
import backend.order_spring_designpatterns.Entity.Product;

import java.math.BigDecimal;

/* Classe responsável por definir o transporte de dados de OrderItem, delimitando informações específicas para resposta
à requisição */
public record OrderItemResponseDTO(Long id, Product product, Long orderId, Integer amount, BigDecimal totalPrice) {
    public OrderItemResponseDTO(OrderItem orderItem){
        this(
                orderItem.getId(),
                orderItem.getProduct(),
                orderItem.getOrder().getId(),
                orderItem.getAmount(),
                orderItem.getTotalPrice()
        );
    }
}
