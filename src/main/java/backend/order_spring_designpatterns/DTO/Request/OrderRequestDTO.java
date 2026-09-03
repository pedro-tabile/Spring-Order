package backend.order_spring_designpatterns.DTO.Request;

import java.util.List;

// Classe responsável por definir o transporte de dados da requisição ao OrderService, delimitando informações específicas
public record OrderRequestDTO (Long clientId, List<OrderItemRequestDTO> orderItems, PaymentRequestDTO paymentDTO) {
}
