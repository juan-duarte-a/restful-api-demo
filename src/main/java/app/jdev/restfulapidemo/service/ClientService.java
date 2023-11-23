package app.jdev.restfulapidemo.service;

import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.mapper.ClientMapper;
import app.jdev.restfulapidemo.repository.ClientRepository;


@Service
public class ClientService extends EntityService<Client, Long> {

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        super(clientRepository, clientMapper);
    }

}
