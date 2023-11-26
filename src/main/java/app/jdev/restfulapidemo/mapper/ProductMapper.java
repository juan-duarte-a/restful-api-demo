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
        var product = new Product(productDTO.name(), productDTO.price());
        product.setId(productDTO.id());
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
        Product product = mapToEntity(dto);
        product.setId(id);
        return product;
    }

}
