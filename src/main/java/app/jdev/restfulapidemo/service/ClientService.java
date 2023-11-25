package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.model.DTO;
import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.mapper.ClientMapper;
import app.jdev.restfulapidemo.repository.ClientRepository;


@Service
public class ClientService extends EntityService<Client, Long> {

    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        super(clientRepository, clientMapper);
        this.clientMapper = clientMapper;
    }

    public Client mapToClient(DTO<Long> clientDTO) {
        return clientMapper.mapToEntity(clientDTO);
    }

}
