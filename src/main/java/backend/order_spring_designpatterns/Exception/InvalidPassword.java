package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando a senha enviada para exclusão de usuário é inválida
public class InvalidPassword extends RuntimeException {
    public InvalidPassword() {
        super("Senha inválida! Não foi possível realizar a operação!");
    }
}
