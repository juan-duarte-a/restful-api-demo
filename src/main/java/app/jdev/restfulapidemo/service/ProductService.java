package app.jdev.restfulapidemo.service;

import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.mapper.ProductMapper;
import app.jdev.restfulapidemo.repository.ProductRepository;

@Service
public class ProductService extends EntityService<Product, Long> {

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
    }

}
