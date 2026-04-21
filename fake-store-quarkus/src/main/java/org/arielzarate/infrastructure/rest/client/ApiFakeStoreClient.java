package org.arielzarate.infrastructure.rest.client;

import org.arielzarate.domain.model.Product;
import org.arielzarate.infrastructure.rest.dto.ProductDto;
import org.arielzarate.infrastructure.rest.mapper.ProductRestMapper;
import org.arielzarate.infrastructure.rest.provider.WebClientMethod;
import org.arielzarate.infrastructure.rest.provider.WebClientProvider;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class ApiFakeStoreClient {

    private static final String BASE_URI = "https://fakestoreapi.com";
    private static final long TIMEOUT = 50000L;

    private final WebClientProvider webClientProvider;

    public ApiFakeStoreClient(WebClientProvider webClientProvider) {
        this.webClientProvider = webClientProvider;
    }

    public List<Product> getAllProducts() {
        ProductDto[] response = webClientProvider.applyWithoutBody(
                "FakeStoreAPI",
                WebClientMethod.GET,
                URI.create(BASE_URI + "/products"),
                TIMEOUT,
                null,
                ProductDto[].class
        );

        return Arrays.stream(response)
                .map(ProductRestMapper::toDomain)
                .toList();
    }

    public Product getProductById(Integer id) {
        ProductDto response = webClientProvider.applyWithoutBody(
                "FakeStoreAPI",
                WebClientMethod.GET,
                URI.create(BASE_URI + "/products/" + id),
                TIMEOUT,
                null,
                ProductDto.class
        );

        return ProductRestMapper.toDomain(response);
    }
}