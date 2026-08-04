package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.OrderRequestDTO;
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

@RestController()// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/orders")
// Classe responsável pelo controle de requisições e respostas da API para operações com pedidos
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    // Annotations Mapping (mapeiam para métodos Java) permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll(){
        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@RequestBody OrderRequestDTO orderDTO, @PathVariable Long id){
        return ResponseEntity.ok(orderService.update(orderDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody OrderRequestDTO orderDTO){
        return ResponseEntity.ok(orderService.insert(orderDTO));
    }
}
