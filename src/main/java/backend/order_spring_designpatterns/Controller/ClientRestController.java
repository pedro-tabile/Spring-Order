package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.DTO.Request.ClientRequestDTO;
import backend.order_spring_designpatterns.DTO.Response.ClientResponseDTO;
import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Service.ClientService;
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

@RestController// Usa-se para indicar o retorno de dados no corpo da resposta HTTP/web
@RequestMapping("/clients")
// Classe responsável pelo controle de requisições e respostas da API para operações com clientes
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    // Annotations Mapping (mapeiam para métodos Java) permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll(){
        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        List<Client> clients = clientService.findAll();
        List<ClientResponseDTO> clientsResponse = clients.stream().map(ClientResponseDTO::new).toList();

        return ResponseEntity.ok(clientsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id){
        Client client = clientService.findById(id);
        ClientResponseDTO clientResponse = new ClientResponseDTO(client);

        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Long id){
        Client client = clientService.update(clientRequestDTO, id);
        ClientResponseDTO clientResponse = new ClientResponseDTO(client);

        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@RequestBody ClientRequestDTO clientRequestDTO){
        Client client = clientService.insert(clientRequestDTO);
        ClientResponseDTO clientResponse = new ClientResponseDTO(client);

        return ResponseEntity.ok(clientResponse);
    }
}
