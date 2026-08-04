package backend.order_spring_designpatterns.DTO;

import java.math.BigDecimal;

// Classe responsável por definir o transporte de dados da requisição ao service, delimitando informações específicas
public class ProductRequestDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
}
