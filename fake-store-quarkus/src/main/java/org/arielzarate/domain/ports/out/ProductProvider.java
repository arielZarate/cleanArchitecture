package org.arielzarate.domain.ports.out;

import org.arielzarate.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductProvider {
    Optional<Product> findById(Integer id);
    List<Product> findAll();
    Product save(Product product);
}