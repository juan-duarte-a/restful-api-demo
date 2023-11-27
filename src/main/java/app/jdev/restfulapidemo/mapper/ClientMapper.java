package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.dto.ClientDTO;
import app.jdev.restfulapidemo.dto.DTO;

@Component
public class ClientMapper implements Mapper<Client, Long> {

    @Override
    public Client mapToEntity(DTO<Long> dto) {
        ClientDTO clientDTO = (ClientDTO) dto;
        var client = new Client(clientDTO.name(), clientDTO.phoneNumber(), clientDTO.email());
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
