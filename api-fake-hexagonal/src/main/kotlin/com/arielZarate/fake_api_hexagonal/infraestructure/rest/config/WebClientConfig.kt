package com.arielZarate.fake_api_hexagonal.infraestructure.rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            //  .baseUrl("https://fakestoreapi.com")
            .build()
    }
}