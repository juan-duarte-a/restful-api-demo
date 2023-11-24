package app.jdev.restfulapidemo.mapper;

import org.springframework.stereotype.Component;

import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.model.ProductDTO;

@Component
public class ProductMapper implements Mapper<Product, Long> {

    @Override
    public Product mapToEntity(DTO<Long> dto) {
        ProductDTO productDTO = (ProductDTO) dto;
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        return product;
    }

    @Override
    public DTO<Long> mapToDTO(Product product) {
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getPrice());
    }

    @Override
    public Product updateAndMapToEntity(Long id, DTO<Long> dto) {
        ProductDTO productDTO = (ProductDTO) dto;
        Product product = new Product(productDTO.name(), productDTO.price());
        product.setId(id);
        return product;
    }

}
