package backend.order_spring_designpatterns.Controller;

import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("clientes")
// Classe responsável pelo controle de requisições e respostas da API para operações com clientes
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    // Annotations Mapping permitem tratar métodos HTTP como PUT, DELETE, CREATE e UPDATE.
    @GetMapping
    public ResponseEntity<Iterable<Client>> findAll(){
        // A classe ResponseEntity representa a resposta HTTP inteira (status e corpo) enviada ao cliente após requisição
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client, @PathVariable Long id){
        return ResponseEntity.ok(clientService.update(client, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.ok().build(); // Retorna resposta sem corpo
    }

    @PostMapping
    public ResponseEntity<Client> delete(@RequestBody Client client){
        return ResponseEntity.ok(clientService.insert(client));
    }
}
