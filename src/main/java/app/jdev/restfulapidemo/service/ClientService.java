package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.model.ClientDTO;
import app.jdev.restfulapidemo.model.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.mapper.ClientMapper;
import app.jdev.restfulapidemo.repository.ClientRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ClientService extends EntityService<Client, Long> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        super(clientRepository, clientMapper);
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Client mapToClient(DTO<Long> clientDTO) {
        return clientMapper.mapToEntity(clientDTO);
    }

    public void existsClientCheck(DTO<Long> dto) {
        ClientDTO clientDTO = (ClientDTO) dto;
        if (clientRepository.existsByNameAndPhoneNumberOrEmail(
                clientDTO.name(), clientDTO.phoneNumber(), clientDTO.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already exists");
        }
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }
}
