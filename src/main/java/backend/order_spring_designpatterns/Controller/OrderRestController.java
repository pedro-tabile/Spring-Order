package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.OrderRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.OrderResponseDTO;
import backend.order_spring_designpatterns.Entity.Order;
import backend.order_spring_designpatterns.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/orders")
// Classe responsável pelo controle de requisições e respostas da API para operações com pedidos
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    // Annotations Mapping (mapeiam para métodos Java) permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll(){
        List<Order> orders = orderService.findAll();
        List<OrderResponseDTO> ordersResponse = orders.stream().map(OrderResponseDTO::new).toList();

        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id){
        Order orderFound = orderService.findById(id);
        OrderResponseDTO orderResponse = new OrderResponseDTO(orderFound);

        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@RequestBody OrderRequestDTO orderRequestDTO, @PathVariable Long id){
        Order orderUpdate = orderService.update(orderRequestDTO, id);
        OrderResponseDTO orderResponse = new OrderResponseDTO(orderUpdate);


        return ResponseEntity.ok(orderResponse);
    }

    // Atualiza o registro com pagamento realizado e status concluído.
    @PutMapping("/{id}/paid")
    public ResponseEntity<OrderResponseDTO> updateOrderPaid(@PathVariable Long id){
        Order orderUpdate = orderService.updatePaid(id);
        OrderResponseDTO orderResponse = new OrderResponseDTO(orderUpdate);

        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@RequestBody OrderRequestDTO orderRequestDTO){
        Order orderSave = orderService.insert(orderRequestDTO);
        OrderResponseDTO orderResponse = new OrderResponseDTO(orderSave);

        return ResponseEntity.ok(orderResponse);
    }
}
