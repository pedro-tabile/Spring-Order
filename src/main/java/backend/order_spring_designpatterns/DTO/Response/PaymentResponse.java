package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Payment;
import backend.order_spring_designpatterns.Service.Enums.PaymentMethodsEnum;
import backend.order_spring_designpatterns.Service.Enums.StatusPaymentEnum;

import java.time.OffsetDateTime;

/* Record responsável por definir o transporte de dados do PaymentService ao controller do Order, delimitando informações
específicas para resposta à requisição */
public record PaymentResponse(Long id,
                              Long orderId,
                              StatusPaymentEnum status,
                              PaymentMethodsEnum type,
                              OffsetDateTime paymentDate) {

    public PaymentResponse(Payment payment){
        this(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getStatus(),
                payment.getType(),
                payment.getPaymentDate()
        );
    }
}
