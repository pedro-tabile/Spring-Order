package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

// Classe responsável por definir a estrutura para resposta da IA (LLM) quando um order é registrado no banco de dados
public record ModelOrderResponseAi(Long orderId,
                                   Client client,
                                   PaymentResponse payment,
                                   BigDecimal totalValue,
                                   StatusOrderEnum status,
                                   OffsetDateTime creationDate,
                                   List<OrderItemResponse> orderItems,
                                   String message) {

    public ModelOrderResponseAi(Order order){
         this(
                 order.getId(),
                 order.getClient(),
                 new PaymentResponse(order.getPayment()),
                 order.getTotalValue(),
                 order.getStatus(),
                 order.getCreationDate(),
                 order.getOrderItems().stream().map(OrderItemResponse::new).toList(),
                 null
         );
    }
}
