package backend.order_spring_designpatterns.Service;

// Interface que estabelece contrato de operações CRUD para Services
public interface CrudService<T, ID, DTO> {
    T insert(DTO entityDTO);

    Iterable<T> findAll();

    T findById(ID id);

    T update(DTO entityDTO, ID id);

    void delete(ID id);
}
