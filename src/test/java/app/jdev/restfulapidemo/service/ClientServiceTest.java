package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.dto.ClientDTO;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    private final ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceTest(ClientService clientService) {
        this.clientService = clientService;
    }

    @Test
    void shouldReturnAClientDTO() {
        when(clientRepository.findById(any(Long.class))).thenAnswer(invocation -> {
            var client = new Client("Client 1", "+543 6534 7658", "client1@mail.com");
            client.setId(invocation.getArgument(0));
            return Optional.of(client);
        });

        var expected = new ClientDTO(1L, "Client 1", "+543 6534 7658", "client1@mail.com");
        assertEquals(expected, clientService.findById(1L));
    }

    @Test
    void shouldReturnAListOfClientDTOs() {
        var clients = new ArrayList<Client>();
        clients.add(new Client(1L, "Client 1", "+123123", "client1@mail.com"));
        clients.add(new Client(2L, "Client 2", "+234234", "client2@mail.com"));
        clients.add(new Client(3L, "Client 3", "+345345", "client3@mail.com"));

        when(clientRepository.findAll()).thenReturn(clients);

        var expected = new ArrayList<DTO<Long>>();
        clients.forEach(client ->
                expected.add(new ClientDTO(
                        client.getId(),
                        client.getName(),
                        client.getPhoneNumber(),
                        client.getEmail()))
        );

        var obtained = new ArrayList<DTO<Long>>();
        clientService.findAll().forEach(obtained::add);

        assertEquals(expected, obtained);
    }

    @Test
    void shouldCreateAndReturnANewClientDTO() {
        var client = new Client(3L, "Client 3", "+345345", "client3@mail.com");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        var clientDTO = new ClientDTO(client.getId(), client.getName(), client.getPhoneNumber(), client.getEmail());
        assertEquals(clientDTO, clientService.save(clientDTO));
    }

}
