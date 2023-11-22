package app.jdev.restfulapidemo.service;

import org.springframework.stereotype.Service;

import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.mapper.ProductMapper;
import app.jdev.restfulapidemo.model.ProductDTO;
import app.jdev.restfulapidemo.repository.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
   
    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void saveProduct(ProductDTO productDTO) {
        productRepository.save(ProductMapper.map(productDTO));
    }

    public void updateProduct(ProductDTO productDTO) {
        findProductById(productDTO.id());
        saveProduct(productDTO);
    }

}
