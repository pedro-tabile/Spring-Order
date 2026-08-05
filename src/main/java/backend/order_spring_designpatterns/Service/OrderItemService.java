package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.OrderItemRequestDTO;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Entity.OrderItem;
import backend.order_spring_designpatterns.Entity.Product;
import backend.order_spring_designpatterns.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;

    public OrderItem insert(OrderItemRequestDTO orderItemDTO, Order order) {
        Product product = productService.findById(orderItemDTO.getProductId());
        Integer productAmount = orderItemDTO.getAmount();
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(productAmount.longValue()));

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setAmount(productAmount);
        orderItem.setTotalPrice(totalPrice);

        /* Uma vez que "order" corresponde ao objeto passado como parâmetro, sendo uma referência ao Order recebido de
        OrderService.insert(), o campo order_id da tabela OrderItem será preenchido pelo JPA com o id gerado para tal
        Order durante sua criação. Isso ocorre porque houve declaração de relacionamente entre as entidades/campos */
        orderItem.setOrder(order);

        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
