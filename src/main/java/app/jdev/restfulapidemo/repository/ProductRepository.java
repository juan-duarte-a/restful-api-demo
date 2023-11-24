package app.jdev.restfulapidemo.repository;

import app.jdev.restfulapidemo.entity.Product;

public interface ProductRepository extends EntityRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);

}
