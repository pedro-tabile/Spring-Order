package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.ProductRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.ProductResponseDTO;
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

import java.util.List;

@RestController()// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/products")
// Classe responsável pelo controle de requisições e respostas da API para operações com produtos
public class ProductRestController {
    @Autowired
    private ProductService productService;

    // Annotations Mapping permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        List<Product> products = productService.findAll();
        List<ProductResponseDTO> productsResponse = products.stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        Product product = productService.findById(id);
        ProductResponseDTO productResponse = new ProductResponseDTO(product);

        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@RequestBody ProductRequestDTO productRequestDTO,
                                                     @PathVariable Long id){
        Product product = productService.update(productRequestDTO, id);
        ProductResponseDTO productResponse = new ProductResponseDTO(product);

        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = productService.insert(productRequestDTO);
        ProductResponseDTO productResponse = new ProductResponseDTO(product);

        return ResponseEntity.ok(productResponse);
    }
}
