package backend.order_spring_designpatterns.DTO.Response;

import backend.order_spring_designpatterns.Entity.Client;

// Record responsável por definir o transporte de dados do service como resposta ao cliente, delimitando informações específicas
public record ClientResponseDTO(Long id, String name, String email) {
    public ClientResponseDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail());
    }
}
