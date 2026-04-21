package org.arielzarate.interfaces.rest;

import lombok.AllArgsConstructor;
import org.arielzarate.domain.model.Product;
import org.arielzarate.domain.ports.in.ProductService;
import org.arielzarate.interfaces.rest.dto.ProductRequest;
import org.arielzarate.interfaces.rest.dto.ProductResponse;
import org.arielzarate.interfaces.rest.mapper.ProductMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GET
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ProductMapper.toResponse(product)).build();
    }

    @POST
    public Response createProduct(ProductRequest request) {
        Product product = ProductMapper.toDomain(request);
        Product created = productService.createProduct(product);
        return Response.status(Response.Status.CREATED)
                .entity(ProductMapper.toResponse(created)).build();
    }
}