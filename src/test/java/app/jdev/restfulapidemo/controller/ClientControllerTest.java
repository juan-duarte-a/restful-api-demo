package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.dto.ClientDTO;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.service.ClientService;
import app.jdev.restfulapidemo.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private OrderService orderService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldGetAllClientsDTOs() throws Exception {
        List<DTO<Long>> clientDTOs = Arrays.asList(
                new ClientDTO(1L, "Client 1", "+123123", "client1@mail.com"),
                new ClientDTO(2L, "Client 2", "+234234", "client2@mail.com"),
                new ClientDTO(3L, "Client 3", "+345345", "client3@mail.com")
        );

        when(clientService.findAll()).thenReturn(clientDTOs);

        String response = mockMvc.perform(get("/clients"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn().getResponse().getContentAsString();

        List<ClientDTO> responseDTOs = objectMapper.readValue(response, new TypeReference<>() {});
        assertEquals(clientDTOs, responseDTOs);
    }

    @Test
    void shouldGetAClientDTO() throws Exception {
        when(clientService.findById(any(Long.class))).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            return new ClientDTO(id, "A client", "+345345", "client3@mail.com");
        });

        mockMvc.perform(get("/clients/3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("A client"))
                .andExpect(jsonPath("$.phoneNumber").value("+345345"))
                .andExpect(jsonPath("$.email").value("client3@mail.com"));
    }

    @Test
    void shouldRespondNotFound() throws Exception {
        when(clientService.findById(any(Long.class))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/clients/3")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateAndReturnAClientDTO() throws Exception {
        DTO<Long> clientDTO = new ClientDTO(5L, "A client", "+567567", "client5@mail.com");
        when(clientService.save(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.name").value("A client"))
                .andExpect(jsonPath("$.phoneNumber").value("+567567"))
                .andExpect(jsonPath("$.email").value("client5@mail.com"));
    }

}
