package backend.order_spring_designpatterns.Entity;

import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

// DefiniçãOo da entidade Order (Pedido)
@Entity
@Table(name = "Orders")
public class Order {
    // Chave primária com auto-incremento
    @Id
    @GeneratedValue
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
    private StatusOrderEnum status;

    @Column(nullable = false)
    private OffsetDateTime creationDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public StatusOrderEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrderEnum status) {
        this.status = status;
    }

    public BigDecimal getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(BigDecimal totalvalue) {
        this.totalvalue = totalvalue;
    }
}
