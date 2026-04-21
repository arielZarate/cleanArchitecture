package com.arielZarate.fake_api_hexagonal.application

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.domain.ports.`in`.ProductService
import com.arielZarate.fake_api_hexagonal.domain.ports.out.ProductProvider
import org.springframework.stereotype.Service


@Service
class ProductUseCase(
    // nota: podria crear un servicio en domain que maneje alguna logiva o validacion si hiciese falta
    private val productProvider: ProductProvider
) : ProductService {

    override fun getProductById(id: Int): Product? {
        return productProvider.findProductById(id)

    }

    override fun getAllProducts(): List<Product> {
        return productProvider.getAllProducts()
    }

    override fun createProduct(product: Product): Product {
        return productProvider.saveProduct(product);
    }

}