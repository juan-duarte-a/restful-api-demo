package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.dto.DTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import app.jdev.restfulapidemo.dto.ProductDTO;
import app.jdev.restfulapidemo.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<DTO<Long>> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public DTO<Long> findProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO<Long> newProduct(@Validated @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public DTO<Long> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        try {
            productService.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "There are orders with the product that are being eliminated. " +
                            "Eliminate those orders first.");
        }
    }

}
