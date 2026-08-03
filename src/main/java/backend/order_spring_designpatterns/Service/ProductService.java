package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Entity.Product;
import backend.order_spring_designpatterns.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService implements CrudService<Product> {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public Product insert(Product product){
        productRepository.save(product);

        return product;
    }

    public Product update(Product product, Long id){
        findById(id);

        return productRepository.save(product);
    }

    public void delete(Long id){
        findById(id);
        productRepository.deleteById(id);
    }
}
