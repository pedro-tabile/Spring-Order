package backend.order_spring_designpatterns.DTO;

// Classe responsável por definir o transporte de dados da requisição ao service, delimitando informações específicas
public class PaymentRequestDTO {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
