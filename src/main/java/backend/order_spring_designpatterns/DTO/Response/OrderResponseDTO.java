package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/* Classe responsável por definir o transporte de dados do OrderService ao controller do Order, delimitando informações
específicas para resposta à requisição */
public record OrderResponseDTO(Long orderId,
                               Client client,
                               PaymentResponseDTO payment,
                               BigDecimal totalValue,
                               StatusOrderEnum status,
                               OffsetDateTime creationDate,
                               List<OrderItemResponseDTO> orderItems) {

    public OrderResponseDTO(Order order){
         this(
                 order.getId(),
                 order.getClient(),
                 new PaymentResponseDTO(order.getPayment()),
                 order.getTotalValue(),
                 order.getStatus(),
                 order.getCreationDate(),
                 order.getOrderItems().stream().map(OrderItemResponseDTO::new).toList()
         );
    }
}
