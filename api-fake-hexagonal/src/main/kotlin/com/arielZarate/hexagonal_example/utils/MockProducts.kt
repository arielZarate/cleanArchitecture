package com.arielZarate.hexagonal_example.utils

import com.arielZarate.hexagonal_example.domain.model.Product

object MockProducts {

    fun getMockProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Product.Rating(rate = 3.9, count = 120)
            ),
            Product(
                id = 2,
                title = "Mens Casual Premium Slim Fit T-Shirts",
                price = 22.3,
                description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg",
                rating = Product.Rating(rate = 4.1, count = 259)
            )
        )
    }
}