package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.ClientRequestDTO;
import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Repository.ClientRepository;
import backend.order_spring_designpatterns.Service.Interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* Classe que define regras de negócio para Client */
@Service
public class ClientService implements CrudService<Client, Long, ClientRequestDTO> {
    @Autowired
    private ClientRepository clientRepository;

    public Iterable<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        // O .get() retorna o valor ou lança uma exceção caso o valor seja null
        return clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public Client insert(ClientRequestDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());

        clientRepository.save(client);
        return client;
    }

    public Client update(ClientRequestDTO clientDTO, Long id){
        Client clientById = findById(id);
        clientById.setEmail(clientDTO.getEmail());
        clientById.setName(clientDTO.getName());

        return clientRepository.save(clientById);
    }

    public void delete(Long id){
        findById(id);
        clientRepository.deleteById(id);
    }
}
