package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

// Record responsável por definir cada item presente nas informações personalizadas dos emails enviados
public record PersonalizationDataRequestDTO(
    String clientName,
    String orderId,
    String status,
    String orderDate,
    String totalValue,
    String paymentMethod
) {

    public PersonalizationDataRequestDTO(String clientName, Long orderId, StatusOrderEnum status,
                                         OffsetDateTime orderDate, BigDecimal totalValue, String paymentMethod) {
        this(
                clientName,
                orderId.toString(),
                status == StatusOrderEnum.PENDING ? "Pendente" : "Concluído",
                orderDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")),
                NumberFormat.getCurrencyInstance().format(totalValue.setScale(2, RoundingMode.HALF_UP)),
                paymentMethod
        );
    }
}
