package app.jdev.restfulapidemo.mapper;

import app.jdev.restfulapidemo.entity.Product;
import app.jdev.restfulapidemo.model.ProductDTO;

public class ProductMapper {
    
    public static Product map(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        return product;
    }

}
