package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.ProductDTO;
import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    private final ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    @Test
    void shouldReturnAProductDTO() {
        when(productRepository.findById(any(Long.class)))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    var product = new Product(id, "P1", 3.33);
                    return Optional.of(product);
                });

        var expected = new ProductDTO(3L, "P1", 3.33);
        var obtained = (ProductDTO) productService.findById(3L);
        assertEquals(expected, obtained);
    }

    @Test
    void shouldReturnAListOfProductDTOs() {
        var products = new ArrayList<Product>();
        products.add(new Product(1L, "P1", 1.33));
        products.add(new Product(2L, "P2", 2.33));
        products.add(new Product(3L, "P3", 3.33));

        when(productRepository.findAll()).thenReturn(products);

        var expected = new ArrayList<DTO<Long>>();
        products.forEach(product ->
                expected.add(new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice()))
        );

        var obtained = new ArrayList<DTO<Long>>();
        productService.findAll().forEach(obtained::add);

        assertEquals(expected, obtained);
    }

    @Test
    void shouldCreateAndReturnANewProductDTO() {
        var product = new Product(3L, "A new product", 10.01);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        var productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        assertEquals(productDTO, productService.save(productDTO));
    }

}
