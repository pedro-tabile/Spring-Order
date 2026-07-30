package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

// Definição da entidade Order (Pedido)
@Entity
public class Order {
    @Id
    private Long id;

    // Definição de relação n-1 com a tabela Client - unidirecional
    @ManyToOne
    // Coluna definida como client_id e como FK
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Definição de relação 1-1 com a tabela Payment
    @OneToOne
    // Coluna definida como payment_id e como FK
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    // Definição de relação 1-n com a tabela OrderItem
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    private BigDecimal totalvalue;

    // Não permite campo vazio ou somente com espaços
    @NotBlank
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private OffsetDateTime creationDate;
}
