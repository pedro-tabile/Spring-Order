package backend.order_spring_designpatterns.Service.Interfaces;

import java.util.List;

// Interface que estabelece contrato de operações CRUD para Services (somente àqueles com endpoint: Product, Client e Order)
public interface CrudService<T, ID, DTO> {
    T insert(DTO entityDTO);

    List<T> findAll();

    T findById(ID id);

    T update(DTO entityDTO, ID id);

    void delete(ID id);
}
