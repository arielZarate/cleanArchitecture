package org.arielzarate.infrastructure.persistence.repository;

import org.arielzarate.infrastructure.persistence.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {

}