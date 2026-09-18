package backend.order_spring_designpatterns.Service.Enums;

import lombok.Getter;

@Getter
// Opções para métodos de pagamento
public enum PaymentMethodsEnum {
    PIX("PIX"),
    ESPECIE("Dinheiro em espécie"),
    CREDITO("Cartão de crédito"),
    DEBITO("Cartão de débito");

    private final String methodName;
    PaymentMethodsEnum(String value) {
        this.methodName = value;
    }
}
