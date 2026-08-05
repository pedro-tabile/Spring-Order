package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.OrderItemRequestDTO;
import backend.order_spring_designpatterns.DTO.OrderRequestDTO;
import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Entity.OrderItem;
import backend.order_spring_designpatterns.Entity.Payment;
import backend.order_spring_designpatterns.Repository.OrderRepository;
import backend.order_spring_designpatterns.Service.Enums.StatusOrderEnum;
import backend.order_spring_designpatterns.Service.Interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements CrudService<Order, Long, OrderRequestDTO> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private PaymentService paymentService;

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    //TODO: payment,
    public Order insert(OrderRequestDTO orderDTO){
        Client client = clientService.findById(orderDTO.getClientId());

        Order order = new Order();
        order.setClient(client);
        order.setStatus(StatusOrderEnum.PENDING);
        order.setCreationDate(OffsetDateTime.now());
        order.setOrderItems(getOrderItemsInRequestList(orderDTO.getOrderItems(), order));
        order.setTotalvalue(order.getOrderItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        Payment payment = paymentService.insert(orderDTO.getPaymentDTO(), order);
        order.setPayment(payment);

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

    public List<OrderItem> getOrderItemsInRequestList(List<OrderItemRequestDTO> orderItemsRequest, Order order){
        List<OrderItem> orderItems = new ArrayList<>();

        for (var item : orderItemsRequest){
           OrderItem orderItem = orderItemService.insert(item, order);
           orderItems.add(orderItem);
        }

        return orderItems;
    }
}
