package com.arielZarate.fake_api_hexagonal.application

import com.arielZarate.fake_api_hexagonal.domain.model.Product
import com.arielZarate.fake_api_hexagonal.domain.ports.out.ProductsPortOut
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.instancio.Instancio


class GetProductUseCaseTest:StringSpec({


    val productsPortOut = mockk<ProductsPortOut>()  //mockk de salida
    val getProductUseCase = GetProductUseCase(productsPortOut)


    //antes de iniciar
    // beforeEach{
    // clearAllMocks()
    // }


     "should get produdct By Id"{
         //GIVEN

         val product = Instancio.of(Product::class.java).create()

         every { product.id?.let { productsPortOut.findProductById(it) } } returns product

         // WHEN
         val result = getProductUseCase.getProductById(product.id ?: 1)

         result shouldBe product

         verify { product.id?.let { productsPortOut.findProductById(it) } }

     }



    "should get all products" {
        // Usamos Instancio para crear una lista de productos aleatorios


        // GIVEN
        val products = Instancio.ofList(Product::class.java).size(2).create()
        every { productsPortOut.getAllProducts() } returns products

        // WHEN
        val result = getProductUseCase.getAllProducts()

        // THEN
        result.size shouldBe 2
        verify { productsPortOut.getAllProducts() }
    }



})

