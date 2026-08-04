package backend.order_spring_designpatterns.DTO;

import backend.order_spring_designpatterns.Entity.OrderItem;

import java.util.List;

// Classe responsável por definir o transporte de dados da requisição ao service, delimitando informações específicas
public class OrderRequestDTO {
    private Long clientId;
    private List<OrderItem> orderItems;
}
