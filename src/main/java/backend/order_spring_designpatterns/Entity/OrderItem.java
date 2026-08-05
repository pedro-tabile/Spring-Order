package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.math.BigDecimal;

// Definição da entidade Order (Pedido)
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
