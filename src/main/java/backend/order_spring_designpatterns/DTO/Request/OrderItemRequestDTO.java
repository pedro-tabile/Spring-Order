package backend.order_spring_designpatterns.DTO.Request;

// Classe responsável por definir o transporte de dados da requisição ao OrderItemService, delimitando informações específicas
public class OrderItemRequestDTO {
    private Long productId;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
