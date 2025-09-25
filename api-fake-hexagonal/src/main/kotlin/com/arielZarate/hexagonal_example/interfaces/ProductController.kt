package com.arielZarate.hexagonal_example.interfaces

import com.arielZarate.hexagonal_example.domain.model.Product;
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.interfaces.mappers.ProductMapper
import com.arielZarate.hexagonal_example.interfaces.model.ProductRequest
import com.arielZarate.hexagonal_example.interfaces.model.ProductResponse
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