package com.arielZarate.fake_api_hexagonal.infraestructure.adapters

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.domain.ports.out.ProductProvider
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.mapper.ProductEntityMapper
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model.ProductEntity
import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.repositories.ProductRepository
import com.arielZarate.fake_api_hexagonal.infraestructure.rest.ApiFakeStoreClient
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductAdapter(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper,
    private val apiFakeStoreClient: ApiFakeStoreClient
) : ProductProvider {

    override fun findProductById(id: Int): Product {
        val productEntity: Optional<ProductEntity> = productRepository.findById(id)
        return if (productEntity.isPresent) {
            productEntityMapper.mapToDomain(productEntity.get())
        } else {
            val productFromApi = apiFakeStoreClient.getProductById(id)
            saveProduct(productFromApi)
        }
    }

    override fun getAllProducts(): List<Product> {
        val productEntities = productRepository.findAll()
        return if (productEntities.isEmpty()) {
            val productsFromApi = apiFakeStoreClient.getAllProducts()
            productsFromApi.map { saveProduct(it) }
        } else {
            productEntities.map { productEntityMapper.mapToDomain(it) }
        }
    }

    override fun saveProduct(product: Product): Product {
        val productSaved = productRepository.save(productEntityMapper.mapToEntity(product))
        return productEntityMapper.mapToDomain(productSaved)
    }
}