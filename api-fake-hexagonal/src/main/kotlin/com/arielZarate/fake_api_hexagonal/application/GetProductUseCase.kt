package com.arielZarate.fake_api_hexagonal.application

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.domain.ports.`in`.GetProductService
import com.arielZarate.fake_api_hexagonal.domain.ports.out.ProductsPortOut
import org.springframework.stereotype.Service


@Service
class GetProductUseCase(
    // nota: podria crear un servicio en domain que maneje alguna logiva o validacion si hiciese falta
    private val productsPortOut: ProductsPortOut
) : GetProductService {

    override fun getProductById(id: Int): Product? {
        return productsPortOut.findProductById(id)

    }

    override fun getAllProducts(): List<Product> {
        return productsPortOut.getAllProducts()
    }

    override fun createProduct(product: Product): Product {
        return productsPortOut.saveProduct(product);
    }

    /*
//private val products = MockProducts.getMockProducts()
override fun getProductById(id: Int): Product? {
    return products.find{it.id==id}
}

override fun getAllProducts(): List<Product> {
    return products
}
* */
}