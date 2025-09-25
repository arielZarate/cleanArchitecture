package com.arielZarate.fake_api_hexagonal.interfaces

import com.arielZarate.fake_api_hexagonal.domain.model.Product;
import com.arielZarate.fake_api_hexagonal.domain.ports.`in`.GetProductService
import com.arielZarate.fake_api_hexagonal.interfaces.mappers.ProductMapper
import com.arielZarate.fake_api_hexagonal.interfaces.model.ProductRequest
import com.arielZarate.fake_api_hexagonal.interfaces.model.ProductResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class ProductController(
    private val getProductService: GetProductService,
    private val productMapper: ProductMapper
) {
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        val products: List<Product> = getProductService.getAllProducts()
        return ResponseEntity.ok(products.map { productMapper.mapToResponse(it) })
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<ProductResponse> {
        val product: Product? = getProductService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product?.let { productMapper.mapToResponse(it) })
    }

    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> {
        val product = productMapper.mapToDomain(productRequest)
        val createdProduct: Product = getProductService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.mapToResponse(createdProduct))
    }
}