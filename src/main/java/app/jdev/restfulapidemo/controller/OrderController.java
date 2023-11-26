package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.NewOrderDTO;
import app.jdev.restfulapidemo.model.OrderDTO;
import app.jdev.restfulapidemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Iterable<DTO<Long>> findAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public DTO<Long> findOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO<Long> newOrder(@RequestBody NewOrderDTO newOrderDTO) {
        return orderService.save(newOrderDTO);
    }

    @PutMapping("/{id}")
    public DTO<Long> updateOrder(@PathVariable Long id, @RequestBody NewOrderDTO newOrderDTO) {
        return orderService.update(id, newOrderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }

}
