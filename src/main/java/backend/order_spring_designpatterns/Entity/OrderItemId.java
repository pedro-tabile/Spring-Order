package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/* Classe necessária para definição de chave primária composta para OrderItem */

/* @Embeddable (incorporável) indica que essa classe será incorporada por um campo de outra entidade */
@Embeddable
public class OrderItemId implements Serializable {
    // Atributos que representam os componentes da chave composta
    private Long productId;
    private Long orderId;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        OrderItemId that = (OrderItemId) object;
        return Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
