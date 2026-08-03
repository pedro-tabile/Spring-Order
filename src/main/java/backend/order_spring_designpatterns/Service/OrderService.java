package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService implements CrudService<Order> {
    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public Order insert(Order order){
        orderRepository.save(order);

        return order;
    }

    public Order update(Order order, Long id){
        findById(id);

        return orderRepository.save(order);
    }

    public void delete(Long id){
        findById(id);
        orderRepository.deleteById(id);
    }
}
