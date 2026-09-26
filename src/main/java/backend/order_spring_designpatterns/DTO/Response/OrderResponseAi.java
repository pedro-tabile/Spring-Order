package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.DTO.Request.OrderItemRequest;
import backend.order_spring_designpatterns.DTO.Request.PaymentRequest;

import java.util.List;

// Classe responsável por definir a estrutura para resposta da IA (LLM)
public record OrderResponseAi(
        Long clientId,
        List<OrderItemRequest> orderItems,
        PaymentRequest payment,
        String message
) {
}
