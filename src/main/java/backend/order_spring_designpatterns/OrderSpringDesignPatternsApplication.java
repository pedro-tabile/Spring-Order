package backend.order_spring_designpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderSpringDesignPatternsApplication {
    //TODO: regras de negócio de pedido (estoque), transaction, implementação de métodos de pagamento, tratamento de erros

    public static void main(String[] args) {
        SpringApplication.run(OrderSpringDesignPatternsApplication.class, args);
    }

    /* O bean (objeto gerenciado pelo Spring) @Component e suas especializações (como @Repository, @Controller e
    @Service) têm escopo Singleton por padrão. */

    /* @Autowired realiza a injeção de dependência automática, permitindo que o Spring forneça um bean (objeto
    gerenciado pelo framework). */

    /* Os atributos marcados com @Autowired (como clientRepository) recebem a referência para um bean do tipo
    especificado, gerenciado pelo Spring. Portanto, esses beans são Singleton e a referência para um atributo de
    determinado tipo em diferentes classes aponta para o mesmo objeto. */
}
