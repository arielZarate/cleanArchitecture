package org.arielzarate.infrastructure.adapters;

import jakarta.transaction.Transactional;
import org.arielzarate.domain.model.Product;
import org.arielzarate.domain.ports.out.ProductProvider;
import org.arielzarate.infrastructure.adapters.mappers.ProductEntityMapper;
import org.arielzarate.infrastructure.persistence.entity.ProductEntity;
import org.arielzarate.infrastructure.persistence.repository.ProductRepository;
import org.arielzarate.infrastructure.rest.client.ApiFakeStoreClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductAdapter implements ProductProvider {

    @Inject
    ProductRepository productRepository;

    @Inject
    ApiFakeStoreClient apiFakeStoreClient;

    @Override
    @Transactional
    public Optional<Product> findById(Integer id) {
        Optional<ProductEntity> entity = productRepository.find("id", id).firstResultOptional();
        return entity.map(ProductEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        List<ProductEntity> entities = productRepository.listAll();

        if (entities.isEmpty()) {
            List<Product> products = apiFakeStoreClient.getAllProducts();
            return products.stream()
                    .map(this::save)
                    .collect(Collectors.toList());
        }

        return entities.stream()
                .map(ProductEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        productRepository.persist(entity);
        return ProductEntityMapper.toDomain(entity);
    }
}