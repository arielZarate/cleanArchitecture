package com.arielZarate.hexagonal_example.infraestructure.persistence.repositories

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.infraestructure.persistence.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository:JpaRepository<ProductEntity,Int> {

    //podria existir otros metodos personalizados
    //podria existir otros metodos personalizados
}