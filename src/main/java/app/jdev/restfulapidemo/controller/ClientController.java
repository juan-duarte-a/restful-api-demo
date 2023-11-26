package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.model.ClientDTO;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.service.ClientService;
import app.jdev.restfulapidemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;

    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping
    public Iterable<DTO<Long>> findAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public DTO<Long> findClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO<Long> newClient(@RequestBody ClientDTO clientDTO) {
        clientService.existsClientCheck(clientDTO);
        return clientService.save(clientDTO);
    }

    @PutMapping("/{id}")
    public DTO<Long> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.update(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteClient(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        orderService.deleteAllOrdersByClient(client);
        clientService.delete(id);
    }

}
