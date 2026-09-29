package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando algum dado não é informado ou é informado incorretamente à IA para definição do json de Order.
public class AiInvalidDataEntered extends RuntimeException {
    public AiInvalidDataEntered(String message) {
        super(message);
    }
}
