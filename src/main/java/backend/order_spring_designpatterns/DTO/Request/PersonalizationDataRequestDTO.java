package backend.order_spring_designpatterns.DTO.Request;

import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Record responsável por definir cada item presente nas informações personalizadas dos emails enviados
public record PersonalizationDataRequestDTO(
    String clientName,
    Long orderId,
    StatusOrderEnum status,
    OffsetDateTime orderDate,
    BigDecimal totalValue,
    String paymentMethod
) {
    
}
