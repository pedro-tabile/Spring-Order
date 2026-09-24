package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando o nome informado não corresponde a nenhum produto registrado
public class ProductNotFoundByName extends RuntimeException {
  public ProductNotFoundByName(String name) {
    super(String.format("O produto informado não está registrado %s", name));
  }
}
