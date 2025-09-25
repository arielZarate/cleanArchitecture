package com.arielZarate.hexagonal_example.infraestructure.adapters


import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.out.ProductsPortOut
import com.arielZarate.hexagonal_example.infraestructure.persistence.mapper.ProductEntityMapper
import com.arielZarate.hexagonal_example.infraestructure.persistence.mapper.RatingEntityMapper
import com.arielZarate.hexagonal_example.infraestructure.persistence.model.ProductEntity
import com.arielZarate.hexagonal_example.infraestructure.persistence.repositories.ProductRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
class ProductsPortOutAdapter(
    private val productRepository: ProductRepository,
    //inyectamos los services
    private val productEntityMapper: ProductEntityMapper,
    private val ratingEntityMapper: RatingEntityMapper
):ProductsPortOut {


    //======debo mapear cada consulta======
    override fun findProductById(id: Int) :Product{
        val productEntity: Optional<ProductEntity> =productRepository.findById(id);
        return productEntityMapper.toDomain(productEntity.get());
    }

    override fun getAllProducts(): List<Product> {

        val productEntities = productRepository.findAll()
        return productEntities.map { 
            productEntity ->  productEntityMapper.toDomain(productEntity)
        }
    }

    override fun saveProduct(product: Product): Product {

        //mapeo el producto que ingresa a entity
        val productE=productEntityMapper.toEntity(product);

        val productG=productRepository.save(productE)//producto guarado esta como entity

        return productEntityMapper.toDomain(productG)
    }


}