package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.model.ClientDTO;
import app.jdev.restfulapidemo.model.DTO;

@Component
public class ClientMapper implements Mapper<Client, Long> {

    @Override
    public Client mapToEntity(DTO<Long> dto) {
        ClientDTO clientDTO = (ClientDTO) dto;
        Client client = new Client();
        client.setId(clientDTO.id());
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPhoneNumber(clientDTO.phoneNumber());
        return client;
    }

    @Override
    public DTO<Long> mapToDTO(Client client) {
        return new ClientDTO(client.getId(),
                client.getName(),
                client.getPhoneNumber(),
                client.getPhoneNumber());
    }

}
