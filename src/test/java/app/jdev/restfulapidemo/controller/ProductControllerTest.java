package app.jdev.restfulapidemo.controller;

import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.dto.ProductDTO;
import app.jdev.restfulapidemo.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldGetAllProductsDTOs() throws Exception {
        List<DTO<Long>> productDTOs = Arrays.asList(
                new ProductDTO(1L, "Product 1", 10.11),
                new ProductDTO(2L, "Product 2", 20.22),
                new ProductDTO(3L, "Product 3", 30.33)
        );

        when(productService.findAll()).thenReturn(productDTOs);

        String response = mockMvc.perform(get("/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn().getResponse().getContentAsString();

        List<ProductDTO> responseDTOs = objectMapper.readValue(response, new TypeReference<>() {});
        assertEquals(productDTOs, responseDTOs);
    }

    @Test
    void shouldGetAProductDTO() throws Exception {
        when(productService.findById(any(Long.class))).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            return new ProductDTO(id, "A product", 30.33);
        });

        mockMvc.perform(get("/products/3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("A product"))
                .andExpect(jsonPath("$.price").value(30.33));
    }

    @Test
    void shouldRespondNotFound() throws Exception {
        when(productService.findById(any(Long.class))).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/products/3")).andExpect(status().isNotFound());
    }

}
