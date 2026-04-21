package org.arielzarate.infrastructure.adapters.mappers;

import org.arielzarate.domain.model.Product;
import org.arielzarate.infrastructure.persistence.entity.ProductEntity;

public class ProductEntityMapper {

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        
        Product product = new Product();
        product.setId(entity.getId());
        product.setTitle(entity.getTitle());
        product.setPrice(entity.getPrice());
        product.setDescription(entity.getDescription());
        product.setCategory(entity.getCategory());
        product.setImage(entity.getImage());
        
        if (entity.getRating() != null) {
            Product.Rating rating = new Product.Rating(
                entity.getRating().getRate(),
                entity.getRating().getCount()
            );
            product.setRating(rating);
        }
        return product;
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;
        
        ProductEntity entity = new ProductEntity();
        entity.setTitle(product.getTitle());
        entity.setPrice(product.getPrice());
        entity.setDescription(product.getDescription());
        entity.setCategory(product.getCategory());
        entity.setImage(product.getImage());
        
        if (product.getRating() != null) {
            ProductEntity.RatingEmbed rating = new ProductEntity.RatingEmbed(
                product.getRating().getRate(),
                product.getRating().getCount()
            );
            entity.setRating(rating);
        }
        return entity;
    }
}