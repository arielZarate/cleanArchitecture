package org.arielzarate.interfaces.rest.mapper;

import org.arielzarate.domain.model.Product;
import org.arielzarate.interfaces.rest.dto.ProductRequest;
import org.arielzarate.interfaces.rest.dto.ProductResponse;

public class ProductMapper {

    public static Product toDomain(ProductRequest request) {
        if (request == null) return null;

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setImage(request.getImage());

        if (request.getRating() != null) {
            Product.Rating rating = new Product.Rating(
                request.getRating().getRate(),
                request.getRating().getCount()
            );
            product.setRating(rating);
        }
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        if (product == null) return null;

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setImage(product.getImage());

        if (product.getRating() != null) {
            ProductResponse.RatingResponse rating = new ProductResponse.RatingResponse(
                product.getRating().getRate(),
                product.getRating().getCount()
            );
            response.setRating(rating);
        }
        return response;
    }
}