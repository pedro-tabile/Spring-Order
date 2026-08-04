package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.math.BigDecimal;

// Definição da entidade Order (Pedido)
@Entity
public class OrderItem {
    /* Indica que o campo é composto por uma chave primária composta a partir do tipo especificado */
    @EmbeddedId
    private OrderItemId id;

    // Definição de relação n-1 com a tabela Product - unidirecional
    @ManyToOne
    @MapsId("productId") // Define que o productId de OrderItemId corresponde a product_id e compõe a chave composta
    // Coluna definida como product_id e como FK
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Definição de relação n-1 com a tabela Order - unidirecional
    @ManyToOne
    @MapsId("orderId") // Define que o productId de OrderItemId corresponde a product_id e compõe a chave composta
    // Coluna definida como order_id e como FK
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;
}
