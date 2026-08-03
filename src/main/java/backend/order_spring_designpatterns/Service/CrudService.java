package backend.order_spring_designpatterns.Service;

// Interface que estabelece contrato de operações CRUD para Services
public interface CrudService<T> {
    T insert(T entity);

    Iterable<T> findAll();

    T findById(Long id);

    T update(T entity, Long id);

    void delete(Long id);
}
