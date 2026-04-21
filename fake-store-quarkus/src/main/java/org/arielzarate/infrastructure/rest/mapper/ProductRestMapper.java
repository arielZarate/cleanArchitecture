package org.arielzarate.infrastructure.rest.mapper;

import org.arielzarate.domain.model.Product;
import org.arielzarate.infrastructure.rest.dto.ProductDto;
import java.util.Arrays;
import java.util.List;

public class ProductRestMapper {

    public static Product toDomain(ProductDto dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());

        if (dto.getRating() != null) {
            Product.Rating rating = new Product.Rating(
                dto.getRating().getRate(),
                dto.getRating().getCount()
            );
            product.setRating(rating);
        }
        return product;
    }

    public static ProductDto toDto(Product product) {
        if (product == null) return null;

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setImage(product.getImage());

        if (product.getRating() != null) {
            ProductDto.RatingDto rating = new ProductDto.RatingDto(
                product.getRating().getRate(),
                product.getRating().getCount()
            );
            dto.setRating(rating);
        }
        return dto;
    }

    public static List<Product> toDomain(ProductDto[] dtos) {
        if (dtos == null) return null;
        return java.util.Arrays.stream(dtos)
            .map(ProductRestMapper::toDomain)
            .collect(java.util.stream.Collectors.toList());
    }
}