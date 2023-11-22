package app.jdev.restfulapidemo.repository;

import org.springframework.data.repository.CrudRepository;

import app.jdev.restfulapidemo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
