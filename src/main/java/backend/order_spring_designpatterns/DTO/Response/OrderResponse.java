package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/* Record responsável por definir o transporte de dados do OrderService ao controller do Order, delimitando informações
específicas para resposta à requisição */
public record OrderResponse(Long orderId,
                            Client client,
                            PaymentResponse payment,
                            BigDecimal totalValue,
                            StatusOrderEnum status,
                            OffsetDateTime creationDate,
                            List<OrderItemResponse> orderItems) {

    public OrderResponse(Order order){
         this(
                 order.getId(),
                 order.getClient(),
                 new PaymentResponse(order.getPayment()),
                 order.getTotalValue(),
                 order.getStatus(),
                 order.getCreationDate(),
                 order.getOrderItems().stream().map(OrderItemResponse::new).toList()
         );
    }
}
