package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.OrderItemRequestDTO;
import backend.order_spring_designpatterns.DTO.Request.OrderRequestDTO;
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

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    //TODO: Transaction
    public Order insert(OrderRequestDTO orderRequest){
        /* Primeira parte da inserção responsável pelo armazenamento de informações not null para geração de id, de
        forma a permitir a inserção de valores OrderItem com a referência a este order criado. */
        Client client = clientService.findById(orderRequest.getClientId());
        Order order = new Order();
        order.setClient(client);
        order.setStatus(StatusOrderEnum.PENDING);
        order.setCreationDate(OffsetDateTime.now());
        order.setPayment(null);

        order = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        for (var item : orderRequest.getOrderItems()){
            OrderItem orderItem = orderItemService.insert(item, order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalValue(order.getOrderItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        Payment payment = paymentService.insert(orderRequest.getPaymentDTO(), order);
        order.setPayment(payment);

        orderRepository.save(order);
        return order;
    }

    //TODO: atualizações conforme insert
    public Order update(OrderRequestDTO orderRequest, Long id){
        Order orderSaved = findById(id);

        Client client = clientService.findById(orderRequest.getClientId());
        orderSaved.setClient(client);

        for (OrderItemRequestDTO item : orderRequest.getOrderItems()){
            OrderItem orderItem = orderItemService.findByProductAndOrderId(item.getProductId(), orderSaved.getId());

            if (orderItem != null){
                orderItemService.updateFromDTOData(item, orderItem);
            } else {
                OrderItem newOrderItem = orderItemService.insert(item, orderSaved);
                orderSaved.getOrderItems().add(newOrderItem);
            }
        }

        orderSaved.setTotalValue(orderSaved.getOrderItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        paymentService.updateById(orderSaved.getPayment().getId(), orderRequest.getPaymentDTO());

        orderRepository.save(orderSaved);
        return orderSaved;
    }

    public void delete(Long id){
        findById(id);
        orderRepository.deleteById(id);
    }
}
