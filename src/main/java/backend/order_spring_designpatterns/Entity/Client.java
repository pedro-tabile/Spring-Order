package backend.order_spring_designpatterns.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

// Definição da entidade Client (Cliente)
@Entity
public class Client {
    // Chave primária com auto-incremento
    @Id
    @GeneratedValue()
    private Long id;

    // Não permite campo vazio ou somente com espaços
    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String email;

    public void setId(Long id) {
        this.id = id;
    }
}
