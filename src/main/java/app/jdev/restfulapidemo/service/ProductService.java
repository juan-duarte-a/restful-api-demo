package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.ProductDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.mapper.ProductMapper;
import app.jdev.restfulapidemo.repository.ProductRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService extends EntityService<Product, Long> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
    }

    public boolean existsProductByName(String name) {
        return productRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public DTO<Long> save(DTO<Long> dto) {
        if (existsProductByName(((ProductDTO) dto).name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The entity already exists");
        }
        return super.save(dto);
    }

}
