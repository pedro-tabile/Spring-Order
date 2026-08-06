package backend.order_spring_designpatterns.DTO.Request;

// Classe responsável por definir o transporte de dados da requisição ao PaymentService, delimitando informações específicas
public class PaymentRequestDTO {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
