package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.entity.Client;
import app.jdev.restfulapidemo.entity.OrderProduct;
import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.mapper.OrderMapper;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.NewOrderDTO;
import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Order;
import app.jdev.restfulapidemo.repository.OrderRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService extends EntityService<Order, Long> {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ClientService clientService,
            ProductService productService, OrderMapper orderMapper) {
        super(orderRepository, orderMapper);
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
        this.orderMapper = orderMapper;
    }

    public DTO<Long> save(NewOrderDTO newOrderDTO) {
        Client client = clientService.mapToClient(clientService.findById(newOrderDTO.clientId()));
        Order order = new Order(newOrderDTO.date(), client);
        List<OrderProduct> orderProducts = new LinkedList<>();

        newOrderDTO.products().forEach(p -> {
            Product product = productService.mapToProduct(productService.findById(p.productId()));
            OrderProduct orderProduct = new OrderProduct(order, product, p.quantity());
            orderProducts.add(orderProduct);
        });

        order.setOrderProducts(orderProducts);
        return orderMapper.mapToDTO(orderRepository.save(order));
    }

}
