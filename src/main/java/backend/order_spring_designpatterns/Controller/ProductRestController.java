package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.ProductRequestDTO;
import backend.order_spring_designpatterns.Entity.Product;
import backend.order_spring_designpatterns.Service.ProductService;
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
@RequestMapping("/products")
// Classe responsável pelo controle de requisições e respostas da API para operações com produtos
public class ProductRestController {
    @Autowired
    private ProductService productService;

    // Annotations Mapping permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductRequestDTO productRequestDTODTO, @PathVariable Long id){
        return ResponseEntity.ok(productService.update(productRequestDTODTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductRequestDTO productRequestDTODTO){
        return ResponseEntity.ok(productService.insert(productRequestDTODTO));
    }
}
