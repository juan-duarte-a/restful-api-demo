package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.entity.OrderProduct;
import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.mapper.OrderMapper;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.NewOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService extends EntityService<Order, Long> {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderProductService orderProductService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ClientService clientService,
            ProductService productService, OrderProductService orderProductService, OrderMapper orderMapper) {
        super(orderRepository, orderMapper);
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.orderMapper = orderMapper;
    }

    public DTO<Long> save(NewOrderDTO newOrderDTO) {
        Order order = newOrderEntity(newOrderDTO);
        return orderMapper.mapToDTO(orderRepository.save(order));
    }

    @Transactional
    public DTO<Long> update(Long id, NewOrderDTO newOrderDTO) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        oldOrder.getOrderProducts().forEach(orderProductService::deleteOrderProduct);
        Order order = newOrderEntity(newOrderDTO);
        order.setId(id);
        return update(id, orderMapper.mapToDTO(order));
    }

    private void updateTotalPrice(Order order) {
        order.setTotalPrice(order.getOrderProducts().stream()
                .mapToDouble(op -> op.getProduct().getPrice() * op.getQuantity())
                .sum());
    }

    private Order newOrderEntity(NewOrderDTO newOrderDTO) {
        Client client = clientService.mapToClient(clientService.findById(newOrderDTO.clientId()));
        var order = new Order(newOrderDTO.date(), client);
        List<OrderProduct> orderProducts = new LinkedList<>();

        newOrderDTO.products().forEach(p -> {
            Product product = productService.mapToProduct(productService.findById(p.productId()));
            var orderProduct = new OrderProduct(order, product, p.quantity());
            orderProducts.add(orderProduct);
        });

        order.setOrderProducts(orderProducts);
        updateTotalPrice(order);
        return order;
    }

    public List<Order> findAllOrdersByClient(Client client) {
        return orderRepository.findAllByClient(client);
    }

    @Transactional
    public void deleteAllOrdersByClient(Client client) {
        findAllOrdersByClient(client).stream()
                .map(Order::getId).forEach(this::delete);
    }

}
