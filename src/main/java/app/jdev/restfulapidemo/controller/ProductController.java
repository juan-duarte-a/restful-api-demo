package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.model.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import app.jdev.restfulapidemo.model.ProductDTO;
import app.jdev.restfulapidemo.service.ProductService;

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
    public DTO<Long> newProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public DTO<Long> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

}
