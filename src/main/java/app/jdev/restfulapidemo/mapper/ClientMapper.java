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
        Client client = new Client(clientDTO.name(), clientDTO.email(), clientDTO.phoneNumber());
        client.setId(clientDTO.id());
        return client;
    }

    @Override
    public DTO<Long> mapToDTO(Client client) {
        return new ClientDTO(client.getId(),
                client.getName(),
                client.getPhoneNumber(),
                client.getEmail());
    }

    @Override
    public Client updateAndMapToEntity(Long id, DTO<Long> dto) {
        Client client = mapToEntity(dto);
        client.setId(id);
        return client;
    }

}
