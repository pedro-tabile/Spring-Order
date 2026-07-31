package backend.order_spring_designpatterns.Repository;

import backend.order_spring_designpatterns.Entity.OrderItem;
import backend.order_spring_designpatterns.Entity.OrderItemId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* Definição de interface que interage com banco de dados e implementa interface com métodos CRUD para o tipo/tabela
especificado */
/* A dependência (Spring Data JPA) cria a implementação dos métodos em tempo de execução */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {
}
