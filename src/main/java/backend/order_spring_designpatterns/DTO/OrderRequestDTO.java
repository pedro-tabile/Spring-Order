package backend.order_spring_designpatterns.DTO;

import java.util.List;

// Classe responsável por definir o transporte de dados da requisição ao service, delimitando informações específicas
public class OrderRequestDTO {
    private Long clientId;
    private List<OrderItemRequestDTO> orderItems;
    private PaymentRequestDTO paymentDTO;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<OrderItemRequestDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentRequestDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentRequestDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }
}
