package com.arielZarate.fake_api_hexagonal.infraestructure.persistence.repositories

import com.arielZarate.fake_api_hexagonal.infraestructure.persistence.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository:JpaRepository<ProductEntity,Int> {

    //podria existir otros metodos personalizados
    //podria existir otros metodos personalizados
}