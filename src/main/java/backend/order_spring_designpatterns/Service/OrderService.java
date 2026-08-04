package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.OrderRequestDTO;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements CrudService<Order, Long, OrderRequestDTO> {
    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    //TODO: definir como serão adicionados os itens do produto e atualizar infos necessárias com base nos campos da entidade
    public Order insert(OrderRequestDTO orderDTO){
        Order order = new Order();

        orderRepository.save(order);
        return order;
    }

    //TODO: atualizações conforme insert
    public Order update(OrderRequestDTO orderDTO, Long id){
        findById(id);

        return new Order();
    }

    public void delete(Long id){
        findById(id);
        orderRepository.deleteById(id);
    }
}
