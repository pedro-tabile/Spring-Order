package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.ProductRequestDTO;
import backend.order_spring_designpatterns.Entity.Product;
import backend.order_spring_designpatterns.Repository.ProductRepository;
import backend.order_spring_designpatterns.Service.Interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements CrudService<Product, Long, ProductRequestDTO> {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    //TODO: atualizar infos necessárias com base nos campos da entidade
    public Product insert(ProductRequestDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        productRepository.save(product);
        return product;
    }

    //TODO: atualizações conforme insert
    public Product update(ProductRequestDTO productDTO, Long id){
        Product productById = findById(id);
        productById.setName(productDTO.getName());
        productById.setPrice(productDTO.getPrice());
        productById.setStock(productDTO.getStock());

        productRepository.save(productById);
        return productById;
    }

    public void delete(Long id){
        findById(id);
        productRepository.deleteById(id);
    }
}
