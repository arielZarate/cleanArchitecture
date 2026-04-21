package org.arielzarate.domain.ports.in;

import org.arielzarate.domain.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer id);

    List<Product> getAllProducts();

    Product createProduct(Product product);
}