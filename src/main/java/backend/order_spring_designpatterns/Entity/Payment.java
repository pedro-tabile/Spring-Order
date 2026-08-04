package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Definição da entidade Payment (Pagamento)
@Entity
public class Payment {
    // Chave primária com auto-incremento
    @Id
    @GeneratedValue()
    private Long id;

    /* Definição de relação 1-1 com a tabela Order e lado não dominante (inverse side) de relacionamento bidirecional,
    sendo controlado/referenciado pelo payment da tabela Order que gerencia a FK */
    @OneToOne(mappedBy = "payment")
    private Order order;

    @Column(nullable = false)
    private BigDecimal valuePayment;

    // Não permite campo vazio ou somente com espaços
    @NotBlank
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private OffsetDateTime paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValuePayment() {
        return valuePayment;
    }

    public void setValuePayment(BigDecimal valuePayment) {
        this.valuePayment = valuePayment;
    }
}
