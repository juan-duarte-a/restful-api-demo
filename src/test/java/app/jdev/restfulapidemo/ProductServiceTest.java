package app.jdev.restfulapidemo;

import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.ProductDTO;
import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.repository.ProductRepository;
import app.jdev.restfulapidemo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                    Product product = new Product(id, "P1", 3.33);
                    return Optional.of(product);
                });

        ProductDTO expected = new ProductDTO(3L, "P1", 3.33);
        ProductDTO obtained = (ProductDTO) productService.findById(3L);
        assertEquals(expected, obtained);
    }

    @Test
    void shouldReturnAListOfProductDTO() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1L, "P1", 1.33));
        products.add(new Product(2L, "P2", 2.33));
        products.add(new Product(3L, "P3", 3.33));

        when(productRepository.findAll()).thenReturn(products);

        List<DTO<Long>> expected = new ArrayList<>();
        IntStream.range(0, 3).forEach(i ->
            expected.add(new ProductDTO(
                    products.get(i).getId(),
                    products.get(i).getName(),
                    products.get(i).getPrice()))

        );

        ArrayList<DTO<Long>> obtained = new ArrayList<>();
        productService.findAll().forEach(obtained::add);

        assertEquals(expected, obtained);
    }

}
