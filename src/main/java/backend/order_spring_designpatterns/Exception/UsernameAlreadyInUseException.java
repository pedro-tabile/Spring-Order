package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando username registrado já está sendo utilizado por outro usuário
public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
