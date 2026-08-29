package backend.order_spring_designpatterns.Service;

import backend.order_spring_designpatterns.DTO.Request.ClientRequestDTO;
import backend.order_spring_designpatterns.Entity.Client;
import backend.order_spring_designpatterns.Repository.ClientRepository;
import backend.order_spring_designpatterns.Service.Interfaces.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Classe que define regras de negócio para Client */
@Service
public class ClientService implements CrudService<Client, Long, ClientRequestDTO> {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum valor encontrado"));
    }

    public Client insert(ClientRequestDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());

        clientRepository.save(client);
        return client;
    }

    public Client update(ClientRequestDTO clientDTO, Long id){
        Client clientById = findById(id);
        clientById.setEmail(clientDTO.email());
        clientById.setName(clientDTO.name());

        return clientRepository.save(clientById);
    }

    public void delete(Long id){
        findById(id);
        clientRepository.deleteById(id);
    }
}
