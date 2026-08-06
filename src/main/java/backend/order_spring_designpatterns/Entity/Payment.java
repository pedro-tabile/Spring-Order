package backend.order_spring_designpatterns.Entity;

import backend.order_spring_designpatterns.Service.Enums.StatusPaymentEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Definição da entidade Payment (Pagamento)
@Entity
public class Payment {
    // Chave primária com auto-incremento
    @Id
    @GeneratedValue
    private Long id;

    // Definição de relação 1-1 com a tabela Order e lado dominante
    @OneToOne
    // Coluna definida como order_id e como FK
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Não permite campo vazio ou somente com espaços
    @Column(nullable = false)
    // Salva o nome do Enum no banco, não um número ordinal
    @Enumerated(value = EnumType.STRING)
    private StatusPaymentEnum status;

    // Não permite campo vazio ou somente com espaços
    @NotBlank
    @Column(nullable = false)
    private String type;

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

    public StatusPaymentEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPaymentEnum status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
