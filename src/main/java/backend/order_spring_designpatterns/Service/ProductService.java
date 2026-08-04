package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.ProductRequestDTO;
import backend.order_spring_designpatterns.Entity.Product;
import backend.order_spring_designpatterns.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements CrudService<Product, Long, ProductRequestDTO> {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    //TODO: atualizar infos necessárias com base nos campos da entidade
    public Product insert(ProductRequestDTO productDTO){
        //productRepository.save(productDTO);

        return new Product();
    }

    //TODO: atualizações conforme insert
    public Product update(ProductRequestDTO productDTO, Long id){
        findById(id);

        return new Product();
    }

    public void delete(Long id){
        findById(id);
        productRepository.deleteById(id);
    }
}
