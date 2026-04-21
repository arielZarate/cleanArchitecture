package org.arielzarate.application;

import lombok.AllArgsConstructor;
import org.arielzarate.domain.model.Product;
import org.arielzarate.domain.ports.in.ProductService;
import org.arielzarate.domain.ports.out.ProductProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@AllArgsConstructor
@ApplicationScoped
public class ProductUseCase implements ProductService {

    private final ProductProvider productProvider;

    @Override
    public Product getProductById(Integer id) {
        return productProvider.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productProvider.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productProvider.save(product);
    }
}