package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Payment;
import backend.order_spring_designpatterns.Service.Enums.StatusPaymentEnum;

import java.time.OffsetDateTime;

public record PaymentResponseDTO(Long id,
                                 Long orderId,
                                 StatusPaymentEnum status,
                                 String type,
                                 OffsetDateTime paymentDate) {

    public PaymentResponseDTO(Payment payment){
        this(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getStatus(),
                payment.getType(),
                payment.getPaymentDate()
        );
    }
}
