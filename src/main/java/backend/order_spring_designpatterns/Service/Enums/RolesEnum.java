package backend.order_spring_designpatterns.Service.Enums;

import lombok.Getter;

@Getter
public enum RolesEnum {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    RolesEnum(String role) {
        this.role = role;
    }
}
