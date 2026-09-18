package backend.order_spring_designpatterns.Exception;

// Exceção lançada quando o id informado não existe na tabela da entidade no banco de dados
public class IdNotFound extends RuntimeException {
    public IdNotFound(String entity, Long id) {
        super(String.format("O id informado não corresponde a nenhum %s: %d", entity, id));
    }
}
