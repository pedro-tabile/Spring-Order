package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

// Definição da entidade Order (Pedido)
@Entity
public class OrderItem {
    @Id
    private Long id;

    // Definição de relação n-1 com a tabela Product - unidirecional
    @ManyToOne
    // Coluna definida como product_id e como FK
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Definição de relação n-1 com a tabela Order - unidirecional
    @ManyToOne
    // Coluna definida como order_id e como FK
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;
}
