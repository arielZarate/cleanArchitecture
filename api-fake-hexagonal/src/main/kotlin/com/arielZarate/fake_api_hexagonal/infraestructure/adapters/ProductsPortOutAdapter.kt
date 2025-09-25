package com.arielZarate.fake_api_hexagonal.infraestructure.adapters

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.domain.ports.out.ProductsPortOut
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.mapper.ProductEntityMapper
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model.ProductEntity
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.repositories.ProductRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductsPortOutAdapter(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper
) : ProductsPortOut {

    override fun findProductById(id: Int): Product {
        val productEntity: Optional<ProductEntity> = productRepository.findById(id);
        return productEntityMapper.mapToDomain(productEntity.get());
    }

    override fun getAllProducts(): List<Product> {
        val productEntities = productRepository.findAll()
        return productEntities.map { productEntity ->
            productEntityMapper.mapToDomain(productEntity)
        }
    }

    override fun saveProduct(product: Product): Product {
        val productSaved = productRepository.save(productEntityMapper.mapToEntity(product))
        return productEntityMapper.mapToDomain(productSaved)
    }


}