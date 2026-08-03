package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* Classe que define regras de negócio para Client */
@Service
public class ClientService implements CrudService<Client> {
    @Autowired
    private ClientRepository clientRepository;

    public Iterable<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public Client insert(Client client){
        clientRepository.save(client);

        return client;
    }

    public Client update(Client client, Long id){
        findById(id);

        return clientRepository.save(client);
    }

    public void delete(Long id){
        findById(id);
        clientRepository.deleteById(id);
    }
}
