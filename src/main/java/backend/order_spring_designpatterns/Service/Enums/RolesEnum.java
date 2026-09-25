package backend.order_spring_designpatterns.Service.Enums;

import lombok.Getter;

// Opções para tipo de usuário
@Getter
public enum RolesEnum {
    MANAGER("MANAGER"),
    STOCKER("STOCKER"),
    CHASIER("CASHIER"),
    USER("USER"),
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private String role;

    RolesEnum(String role) {
        this.role = role;
    }
}
